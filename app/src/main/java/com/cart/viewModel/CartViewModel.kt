package com.cart.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cart.ui.CartUI
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import com.utils.sharedPrefs.AppPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val appPrefs: AppPrefs
) : ViewModel() {



    val productInCart = combine(
        store.stateFlow.map { it.productList },
        store.stateFlow.map { it.cartIds },
    ) { products, cartIds ->

        val cartProducts = products.filter { product ->
            cartIds.contains(product.id)
        }
        val cartAmount = cartProducts.sumOf { it.price }

        CartUI(
            cartProducts = cartProducts,
            totalAmount = cartAmount
        )
    }
}