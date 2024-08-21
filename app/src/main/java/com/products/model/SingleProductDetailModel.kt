package com.products.model

data class SingleProductScreenUI(
    val product: Product?,
    val inCartStatus: Boolean = false,
    val isFav: Boolean = false,
    val relatedProducts: List<Product> = emptyList()

)
