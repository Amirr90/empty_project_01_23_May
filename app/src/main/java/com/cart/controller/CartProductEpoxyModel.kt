package com.cart.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.bumptech.glide.Glide
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.CartViewBinding
import com.products.listeners.ProductClickListener
import com.products.mapper.appendDollar
import com.products.model.Product
import com.utils.appLevel.App


data class CartProductEpoxyModel(
    val product: Product,
    val cartIconClicked: ProductClickListener
) : ViewBindingKotlinModel<CartViewBinding>(R.layout.cart_view) {
    override fun CartViewBinding.bind() {

        product.let { prod ->
            cartProduct = product
            Glide.with(App.instance).load(prod.thumbnail).into(imageView3)
            textView13.text = prod.price.appendDollar()
        }

        imageView7.setOnClickListener {
            cartIconClicked.onCartIconClicked(product.id)
        }
        imageView3.setOnClickListener {
            cartIconClicked.onProductItemClicked(product)
        }


    }
}