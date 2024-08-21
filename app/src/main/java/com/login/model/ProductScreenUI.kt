package com.login.model

import com.products.model.Product
import com.products.model.ProductCategoryModel

data class ProductScreenUI(
    val productUI: List<ProductUI>,
    val categoryList: List<ProductCategoryModel> = emptyList(),
    val selectedCategory: String


)

data class ProductUI(
    val product: Product,
    val inCart: Boolean = false,
)
