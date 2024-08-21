package com.category.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.category.ui.CategoryScreenUI
import com.category.viewModel.CategoryViewModel

class CategoryScreenController(val viewModel: CategoryViewModel) :
    TypedEpoxyController<CategoryScreenUI>() {
    override fun buildModels(productData: CategoryScreenUI?) {

        if (productData == null)
            return

        productData.categoryUI.forEach {
            CategoryListingEpoxyModel(it) { catId, slug ->
                viewModel.updateSelectedCategory(catId)
                viewModel.fetchProductByCategory(slug)
            }.id(it.category.slug).addTo(this)
        }
    }

}