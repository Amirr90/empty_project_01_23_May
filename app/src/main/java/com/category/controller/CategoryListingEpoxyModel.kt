package com.category.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.category.ui.CategoryUI
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.CategoryListingUiBinding
import com.utils.appLevel.App


data class CategoryListingEpoxyModel(
    val categoryUI: CategoryUI,
    val onCategoryClicked: (catId: Int, slug: String) -> Unit
) : ViewBindingKotlinModel<CategoryListingUiBinding>(R.layout.category_listing_ui) {
    override fun CategoryListingUiBinding.bind() {

        var bgDrawable = 0
        var bgColor = R.color.white
        val tvSize: Float

        if (categoryUI.isSelected) {
            tvSize = 20.0f
            bgColor = R.color.light_blue_A200
            bgDrawable = R.drawable.soft_corners
        } else {
            tvSize = 16.0f
            R.color.white
            bgDrawable = R.drawable.white_bg

        }

        textView19.apply {
            text = categoryUI.category.name
            setOnClickListener {
                onCategoryClicked.invoke(categoryUI.category.id, categoryUI.category.slug)
            }
            // setBackgroundColor(App.instance.resources.getColor(bgColor))
            setBackgroundResource(bgDrawable)
            textSize = tvSize
        }

    }
}