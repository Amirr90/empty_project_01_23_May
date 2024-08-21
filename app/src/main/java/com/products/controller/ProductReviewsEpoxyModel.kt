package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.bumptech.glide.Glide
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ProductCategoryBinding
import com.emptyprojectt1.databinding.ProductListingBinding
import com.emptyprojectt1.databinding.ProductReviewsViewBinding
import com.emptyprojectt1.databinding.SingleProductImageViewBinding
import com.login.model.ProductScreenUI
import com.products.mapper.appendDollar
import com.products.model.ProductCategoryModel
import com.products.model.Review
import com.utils.appLevel.App


data class ProductReviewsEpoxyModel(
    val reviewModel: Review,
) : ViewBindingKotlinModel<ProductReviewsViewBinding>(R.layout.product_reviews_view) {
    override fun ProductReviewsViewBinding.bind() {
        review = reviewModel
        ratingBar.numStars = reviewModel.rating
    }
}