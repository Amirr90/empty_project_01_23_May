package com.favourite.viewModel

import androidx.lifecycle.ViewModel
import com.favourite.ui.FavouriteProductUI
import com.login.model.ProductUI
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavouriteProductViewModel @Inject constructor(
    val store: Store<ApplicationState>
) : ViewModel() {


    val favouriteProducts = combine(
        store.stateFlow.map { it.favProductIds },
        store.stateFlow.map { it.productList },
        store.stateFlow.map { it.cartIds },
    ) { favProductIds, allProducts, cartIds ->

        allProducts.filter {
            favProductIds.contains(it.id)
        }.toSet().toList().map {
            ProductUI(
                product = it,
                inCart = cartIds.contains(it.id)
            )
        }

    }
}