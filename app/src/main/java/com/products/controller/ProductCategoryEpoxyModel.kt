package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.BannerViewBinding
import com.emptyprojectt1.databinding.ProductCategoryBinding
import com.emptyprojectt1.databinding.StaticCategoryViewBinding
import com.products.model.ProductCategoryModel
import com.utils.appLevel.App


data class ProductCategoryEpoxyModel(
    val category: ProductCategoryModel,
    val isSelected: Boolean,
    val onCategoryClicked: (categoryId: String) -> Unit

) : ViewBindingKotlinModel<ProductCategoryBinding>(R.layout.product_category) {
    override fun ProductCategoryBinding.bind() {
        textView18.text = category.name
        profileImage.setOnClickListener {
            onCategoryClicked.invoke(category.slug)
        }

        val borderWidth = if (isSelected) 6 else 1
        val borderColor = if (isSelected) R.color.light_blue_600 else R.color.black
        profileImage.borderWidth = borderWidth
        profileImage.borderColor = App.instance.getColor(borderColor)

    }


}

data class ProductStaticCategoryEpoxyModel(
    val model: CategoryModel,
    val onCategoryClicked: (categoryId: String) -> Unit

) : ViewBindingKotlinModel<StaticCategoryViewBinding>(R.layout.static_category_view) {
    override fun StaticCategoryViewBinding.bind() {
        root.setOnClickListener {
            onCategoryClicked.invoke("")
        }
        category = model
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}

data class BannerEpoxyModel(
    val imageUrl: String, val onCategoryClicked: () -> Unit

) : ViewBindingKotlinModel<BannerViewBinding>(R.layout.banner_view) {
    override fun BannerViewBinding.bind() {
        Glide.with(App.instance).load(imageUrl).apply(
            RequestOptions().placeholder(R.drawable.banner).error(R.drawable.banner)
        ).into(banner)

    }
}