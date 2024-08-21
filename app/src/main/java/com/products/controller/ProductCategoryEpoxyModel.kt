package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ProductCategoryBinding
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