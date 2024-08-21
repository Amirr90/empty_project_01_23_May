package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.AddtocartBuyNowViewBinding
import com.products.listeners.ProductClickListener
import com.products.model.SingleProductScreenUI


data class ProductBuyAndAddToCartButtonEpoxyModel(
    val clickListener: ProductClickListener,
    val productUI: SingleProductScreenUI,
    val gotoCart: () -> Unit
) : ViewBindingKotlinModel<AddtocartBuyNowViewBinding>(R.layout.addtocart_buy_now_view) {
    override fun AddtocartBuyNowViewBinding.bind() {
        val addToCartText = if (productUI.inCartStatus) "goto cart" else "Add to cart"
        btnAddToCart.text = addToCartText


        btnAddToCart.setOnClickListener {
            if (productUI.inCartStatus)
                gotoCart.invoke()
            else
                productUI.product?.let { it1 -> clickListener.onCartIconClicked(it1.id) }
        }
        btnBuyNow.setOnClickListener {

        }

    }
}