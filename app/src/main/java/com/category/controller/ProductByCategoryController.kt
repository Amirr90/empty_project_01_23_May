package com.category.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.category.ui.CategoryScreen
import com.category.ui.CategoryScreenUI

class ProductByCategoryController(private val productClickListener: CategoryScreen) :
    TypedEpoxyController<CategoryScreenUI>() {
    override fun buildModels(productData: CategoryScreenUI?) {

        if (productData == null)
            return

        productData.productByCategory.forEach {
            ProductByCategoryEpoxyModel(it, productClickListener).id(it.id).addTo(this)
        }
    }

}