package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.bumptech.glide.Glide
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ProductListingBinding
import com.login.model.ProductUI
import com.products.listeners.ProductClickListener
import com.products.mapper.appendDollar
import com.utils.appLevel.App


data class ProductEpoxyModel(
    val productScreenUI: ProductUI,
    val productClickListener: ProductClickListener
) : ViewBindingKotlinModel<ProductListingBinding>(R.layout.product_listing) {
    override fun ProductListingBinding.bind() {
        val product = productScreenUI.product



        product.let { prod ->
            products = prod
            Glide.with(App.instance).load(prod.thumbnail).into(imageView)
            textView9.text = prod.price.appendDollar()

            imageView2.setOnClickListener {
                productClickListener.onCartIconClicked(prod.id)
            }
            val cartImage = when (productScreenUI.inCart) {
                true -> R.drawable.cart_filled
                else -> R.drawable.cart_empty
            }
            imageView2.setImageResource(cartImage)


            mainProductView.setOnClickListener {
                productClickListener.onProductItemClicked(prod)
            }
        }

    }
}