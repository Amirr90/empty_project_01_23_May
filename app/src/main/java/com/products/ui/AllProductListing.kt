package com.products.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.emptyprojectt1.databinding.AllProductListingScreenBinding
import com.login.model.ProductUI
import com.navigationController.navigateToProductDetailScreen
import com.products.controller.AllProductListController
import com.products.listeners.ProductClickListener
import com.products.model.Product
import com.products.viewModel.ProductViewModel
import com.utils.flowCollector.collectFlow
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AllProductListing : Fragment(), ProductClickListener {

    lateinit var binding: AllProductListingScreenBinding
    lateinit var controller: AllProductListController
    private lateinit var viewModel: ProductViewModel

    @Inject
    lateinit var store: Store<ApplicationState>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = AllProductListingScreenBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        controller = AllProductListController(this, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setController(controller)
        observers()
        search()
    }

    private fun search() {
        val allData = store.stateFlow.value.productList
        val cartIds = store.stateFlow.value.cartIds

        binding.apply {
            etSearch.addTextChangedListener {
                val query = it?.toString() ?: ""

                val data = if (query.isNotEmpty()) {
                    allData.filter { product ->
                        product.title.toLowerCase(Locale.ROOT).contains(query, ignoreCase = true)
                    }
                } else allData


                val oldData = controller.currentData
                val productUi = data.map { product ->
                    ProductUI(
                        product = product, inCart = cartIds.contains(product.id)
                    )
                }
                oldData?.apply {
                    controller.setData(
                        oldData.copy(
                            productUI = productUi
                        )
                    )
                }

            }
        }
    }

    private fun observers() {
        collectFlow(viewModel.products) {
            controller.setData(it)
        }

        collectFlow(viewModel.isLoading) {
            /* binding.progressBar5.isVisible = it*/
        }
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun onCartIconClicked(id: Int) {
        viewModel.updateCart(id = id)

    }

    override fun onProductItemClicked(product: Product) {
        navigateToProductDetailScreen(product)
    }
}