package com.favourite.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.cart.ui.CartScreen
import com.cart.ui.CartUI
import com.favourite.ui.FavouriteProductUI
import com.login.model.ProductUI
import com.products.controller.ProductEpoxyModel
import com.products.controller.RelatedProductEpoxyModel
import com.products.controller.TitleEpoxyModel
import com.products.listeners.ProductClickListener

class FavouriteProductController(private val productClickListener: ProductClickListener) :
    TypedEpoxyController<List<ProductUI>>() {
    override fun buildModels(productData: List<ProductUI>?) {

        if (productData == null)
            return

        TitleEpoxyModel("Total Product(${productData.size})")
            .id("favouriteProducts")
            .addIf(productData.isNotEmpty(), this)


        productData.forEach {
            ProductEpoxyModel(it, productClickListener).id(
                it.product.id
            ).addTo(this)
        }
    }

}