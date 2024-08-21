package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.bumptech.glide.Glide
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ProductCategoryBinding
import com.emptyprojectt1.databinding.ProductListingBinding
import com.emptyprojectt1.databinding.RelatedProductViewBinding
import com.emptyprojectt1.databinding.SingleProductImageViewBinding
import com.login.model.ProductScreenUI
import com.products.listeners.ProductClickListener
import com.products.mapper.appendDollar
import com.products.model.Product
import com.products.model.ProductCategoryModel
import com.utils.appLevel.App


data class RelatedProductEpoxyModel(
    val product: Product,
    val clickListener: ProductClickListener
) : ViewBindingKotlinModel<RelatedProductViewBinding>(R.layout.related_product_view) {
    override fun RelatedProductViewBinding.bind() {
        relatedProduct = product

        mainViewRelatedProduct.setOnClickListener {
            clickListener.onProductItemClicked(product)
        }
    }
}