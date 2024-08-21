package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.bumptech.glide.Glide
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ProductCategoryBinding
import com.emptyprojectt1.databinding.ProductListingBinding
import com.emptyprojectt1.databinding.SingleProductImageViewBinding
import com.login.model.ProductScreenUI
import com.products.mapper.appendDollar
import com.products.model.ProductCategoryModel
import com.utils.appLevel.App


data class SingleImageEpoxyModel(
    val imageUrl: String,
) : ViewBindingKotlinModel<SingleProductImageViewBinding>(R.layout.single_product_image_view) {
    override fun SingleProductImageViewBinding.bind() {
        image = imageUrl
    }
}