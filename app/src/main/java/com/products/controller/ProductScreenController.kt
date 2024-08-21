package com.products.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.login.model.ProductScreenUI
import com.products.listeners.ProductClickListener
import com.products.viewModel.ProductViewModel

class ProductScreenController(
    private var productClickListener: ProductClickListener,
    val viewModel: ProductViewModel
) :
    TypedEpoxyController<ProductScreenUI>() {
    override fun buildModels(productData: ProductScreenUI?) {

        if (productData == null)
            return

        val categoryItems = productData.categoryList.map {
            ProductCategoryEpoxyModel(it, it.slug == productData.selectedCategory) { slug ->
                viewModel.filterProduct(slug)
            }.id(it.slug)
        }

        CarouselModel_()
            .models(categoryItems)
            .numViewsToShowOnScreen(5.0f)
            .id("ProductCategory")
            .addIf(productData.productUI.isNotEmpty(), this)

        TitleEpoxyModel("Products found(${productData.productUI.size})")
            .id("ProductTitle")
            .addIf(productData.productUI.isNotEmpty(), this)



        productData.productUI.forEach {
            ProductEpoxyModel(it, productClickListener).id(
                it.product.id
            ).addTo(this)
        }
    }


}