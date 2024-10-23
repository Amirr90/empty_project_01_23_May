package com.emptyprojectt1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.emptyprojectt1.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.utils.flowCollector.collectFlow
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import com.utils.sharedPrefs.AppPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var cartMenuItem: MenuItem
    private lateinit var favMenuItem: MenuItem
    private lateinit var badge: BadgeDrawable
    private lateinit var favBadge: BadgeDrawable

    @Inject
    lateinit var store: Store<ApplicationState>

    @Inject
    lateinit var appPrefs: AppPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appPrefs.userId = "6"

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.productListScreen, R.id.cartScreen))

        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setupWithNavController(navController)

        binding.apply {
            cartMenuItem = bottomNavigation.menu.findItem(R.id.cartScreen)
            favMenuItem = bottomNavigation.menu.findItem(R.id.favouriteProductScreen)

            badge = bottomNavigation.getOrCreateBadge(cartMenuItem.itemId)
            favBadge = bottomNavigation.getOrCreateBadge(favMenuItem.itemId)
        }

        setUpBadge(99)
        setUpFavBadge(99)


        val mainDestinationChangedListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                binding.bottomNavigation.isVisible =/*destination.id == R.id.cartScreen
                                ||*/ destination.id == R.id.productListScreen
                            || destination.id == R.id.categoryScreen
                            || destination.id == R.id.favouriteProductScreen
            }

        navController.addOnDestinationChangedListener(mainDestinationChangedListener)


        collectFlow(store.stateFlow.map { it.cartIds }) {
            setUpBadge(it.size)
        }

        collectFlow(store.stateFlow.map { it.favProductIds }) {
            setUpFavBadge(it.size)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setUpBadge(cartCount: Int) {
        if (this::badge.isInitialized) {
            badge.let {
                it.isVisible = cartCount > 0
                it.number = cartCount
            }
        }
    }

    private fun setUpFavBadge(count: Int) {
        if (this::favBadge.isInitialized) {
            favBadge.let {
                it.isVisible = count > 0
                it.number = count
            }
        }
    }
}