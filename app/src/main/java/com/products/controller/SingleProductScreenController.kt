package com.products.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.products.listeners.OnFavouriteClickListener
import com.products.listeners.ProductClickListener
import com.products.model.SingleProductScreenUI

class SingleProductScreenController(
    private val productClickListener: ProductClickListener,
    private val favClickListener: OnFavouriteClickListener,
    private val gotoCart: () -> Unit
) :
    TypedEpoxyController<SingleProductScreenUI>() {
    override fun buildModels(productData: SingleProductScreenUI?) {

        if (productData == null)
            return

        if (productData.product == null)
            return


        val singleImageItems = productData.product.images.map {
            SingleImageEpoxyModel(it).id(it)
        }

        CarouselModel_()
            .models(singleImageItems)
            .numViewsToShowOnScreen(1.0f)
            .id("SingleImage")
            .addTo(this)


        SingleProductDetailEpoxyModel(
            productData,
            favClickListener
        ).id("SingleProduct")
            .addTo(this)


        val productReviewItems = productData.product.reviews.map {
            ProductReviewsEpoxyModel(it).id(it.comment)
        }


        //ProductReviews
        CarouselModel_()
            .models(productReviewItems)
            .numViewsToShowOnScreen(1.3f)
            .id("ProductReviews")
            .addTo(this)


        if (productData.relatedProducts.isNotEmpty()) {
            //Related Products
            TitleEpoxyModel("Related Products(${productData.relatedProducts.size})")
                .id(productData.product.id)
                .addTo(this)


            val relatedProductItems = productData.relatedProducts.map {
                RelatedProductEpoxyModel(it, productClickListener).id(it.id)
            }

            CarouselModel_()
                .models(relatedProductItems)
                .numViewsToShowOnScreen(1.5f)
                .id("RelatedProduct")
                .addTo(this)
        }


        //Add to cart And Buy Now Button Button
        ProductBuyAndAddToCartButtonEpoxyModel(productClickListener, productData) {
            gotoCart.invoke()
        }
            .id("ButtonViews")
            .addTo(this)


    }


}