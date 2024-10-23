package com.products.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.login.model.ProductScreenUI
import com.products.listeners.ProductClickListener
import com.products.viewModel.ProductViewModel


class AllProductListController(
    private var productClickListener: ProductClickListener, val viewModel: ProductViewModel

) : TypedEpoxyController<ProductScreenUI>() {
    override fun buildModels(productData: ProductScreenUI?) {
        if (productData == null) return

        productData.productUI.forEach {
            AllProductEpoxyModel(it, productClickListener).id(it.product.id).addTo(this)
        }

    }
}


