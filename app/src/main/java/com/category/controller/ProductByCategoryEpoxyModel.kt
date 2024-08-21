package com.category.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.category.ui.CategoryScreenUI
import com.category.ui.CategoryUI
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.CategoryListingUiBinding
import com.emptyprojectt1.databinding.CategoryProductListingBinding
import com.products.listeners.ProductClickListener
import com.products.model.Product
import com.utils.appLevel.App


data class ProductByCategoryEpoxyModel(
    val categoryUI: Product,
    val productClickListener: ProductClickListener
) : ViewBindingKotlinModel<CategoryProductListingBinding>(R.layout.category_product_listing) {
    override fun CategoryProductListingBinding.bind() {
        product = categoryUI

        mainView.setOnClickListener {
            productClickListener.onProductItemClicked(categoryUI)
        }
    }
}