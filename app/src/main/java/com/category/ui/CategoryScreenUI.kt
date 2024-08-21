package com.category.ui

import com.products.model.Product
import com.products.model.ProductCategoryModel

data class CategoryScreenUI(
    val categoryUI: List<CategoryUI>,
    val productByCategory: List<Product>,

)

data class CategoryUI(
    val category: ProductCategoryModel,
    val isSelected: Boolean = false
)
