package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.bumptech.glide.Glide
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ProductListingBinding
import com.emptyprojectt1.databinding.SingleProductViewBinding
import com.login.model.ProductUI
import com.products.listeners.OnFavouriteClickListener
import com.products.listeners.ProductClickListener
import com.products.mapper.appendDollar
import com.products.model.SingleProductScreenUI
import com.utils.appLevel.App


data class SingleProductDetailEpoxyModel(
    val productScreenUI: SingleProductScreenUI,
    private val favClickListener: OnFavouriteClickListener
) : ViewBindingKotlinModel<SingleProductViewBinding>(R.layout.single_product_view) {
    override fun SingleProductViewBinding.bind() {
        singleProduct = productScreenUI.product

        checkBox.setOnClickListener {
            productScreenUI.product?.id?.let { it1 -> favClickListener.onFavouriteIconClick(it1) }
        }

        checkBox.isChecked = productScreenUI.isFav


    }
}