package com.cart.mapper

import com.cart.dto.CartResponseDTO
import com.cart.model.CartProductModel
import java.math.RoundingMode
import java.text.DecimalFormat

fun CartResponseDTO.toCartProducts(): List<CartProductModel> {

    return this.carts[0].products.map {
        CartProductModel(
            discountPercentage = it.discountPercentage,
            discountedTotal = it.discountedTotal,
            id = it.id,
            price = it.price,
            quantity = it.quantity,
            thumbnail = it.thumbnail,
            title = it.title,
            total = it.total

        )
    }
}

