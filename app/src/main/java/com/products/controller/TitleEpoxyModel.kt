package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.bumptech.glide.Glide
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ProductCategoryBinding
import com.emptyprojectt1.databinding.ProductListingBinding
import com.emptyprojectt1.databinding.SingleProductImageViewBinding
import com.emptyprojectt1.databinding.TitleViewBinding
import com.login.model.ProductScreenUI
import com.products.mapper.appendDollar
import com.products.model.ProductCategoryModel
import com.utils.appLevel.App


data class TitleEpoxyModel(
    val text: String,
) : ViewBindingKotlinModel<TitleViewBinding>(R.layout.title_view) {
    override fun TitleViewBinding.bind() {
        title = text
    }
}