package com.products.mapper

import com.products.dto.ProductResponseDTO
import com.products.dto.category.ProductCategoryResponseDTO
import com.products.model.Product
import com.products.model.ProductCategoryModel
import com.products.model.Review
import java.math.RoundingMode
import java.text.DecimalFormat


fun getProduct(it: com.products.dto.Product): Product {
    return Product(
        id = it.id,
        title = it.title,
        description = it.description,
        price = it.price,
        rating = it.rating,
        tags = it.tags,
        stock = it.stock,
        thumbnail = it.thumbnail,
        category = it.category,
        discountPercentage = it.discountPercentage,
        images = it.images,
        brand = it.brand,
        returnPolicy = it.returnPolicy,
        warrantyInformation = it.warrantyInformation,
        shippingInformation = it.shippingInformation,
        availabilityStatus = it.availabilityStatus,

        width = it.dimensions.width,
        height = it.dimensions.height,
        depth = it.dimensions.depth,

        reviewCount = it.reviews.size,
        reviews = it.reviews.map { r ->
            Review(
                comment = r.comment,
                date = r.date,
                rating = r.rating,
                reviewerEmail = r.reviewerEmail,
                reviewerName = r.reviewerName,
            )
        }
    )
}

fun ProductResponseDTO.toProductList(): List<Product> {

    return this.products.map {
        getProduct(it)
    }
}


fun com.products.dto.Product.toProduct(): Product {
    return getProduct(this)
}

fun Double.appendDollar(): String {
    return "\$${this.roundOffDecimal()}"
}


fun Double.roundOffDecimal(): Double {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}


fun ProductCategoryResponseDTO.toCategoryList(): List<ProductCategoryModel> {

    val list: MutableList<ProductCategoryModel> = ArrayList()
    repeat(this.size - 1) {
        val model = this[it]
        val data = ProductCategoryModel(
            slug = model.slug,
            name = model.name,
            url = model.url,
            id = it
        )

        list.add(data)
    }
    return list
}