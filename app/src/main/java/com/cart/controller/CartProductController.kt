package com.cart.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.cart.ui.CartScreen
import com.cart.ui.CartUI
import com.products.listeners.ProductClickListener

class CartProductController(private val cartIconClicked: ProductClickListener) :
    TypedEpoxyController<CartUI>() {
    override fun buildModels(cartData: CartUI?) {
        cartData?.cartProducts?.forEach {
            CartProductEpoxyModel(it, cartIconClicked)
                .id(it.id).addTo(this)
        }
    }

}