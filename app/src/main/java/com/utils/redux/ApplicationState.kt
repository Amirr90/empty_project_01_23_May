package com.utils.redux

import com.cart.model.CartProductModel
import com.login.model.LoginResponse
import com.login.model.LoginUI
import com.products.model.Product
import com.products.model.ProductCategoryModel

data class ApplicationState(
    val loginRes: LoginResponse = LoginResponse(),
    val token: String = "",
    val productList: List<Product> = emptyList(),
    val relatedProducts: List<Product> = emptyList(),
    val productByCategory: List<Product> = emptyList(),
    val productCategory: List<ProductCategoryModel> = emptyList(),
    val cartProductList: List<Product> = emptyList(),
    val cartIds: Set<Int> = setOf(1, 3, 5),
    val favProductIds: Set<Int> = setOf(2, 5, 7, 9, 10),
    val selectedCategoryIds: Set<Int> = setOf(0),
    val selectedCategoryForHome: String = "",
    val cartAmount: Double = 0.0,
    val product: Product? = null
)
