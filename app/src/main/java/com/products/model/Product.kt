package com.products.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val stock: Int,
    val title: String,
    val thumbnail: String,
    val description: String,
    val category: String,
    val warrantyInformation: String = "",
    val shippingInformation: String = "",
    val availabilityStatus: String = "",

    val width: Double = 0.0,
    val height: Double = 0.0,
    val depth: Double = 0.0,

    val returnPolicy: String = "",
    val brand: String? = null,
    val price: Double,
    val rating: Double,
    val discountPercentage: Double,
    val tags: List<String> = emptyList(),
    val images: List<String> = emptyList(),
    val reviews: List<Review> = emptyList(),
    val reviewCount: Int = 0,
    val quantity: Int = 1
) : Parcelable

@Parcelize
data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
) : Parcelable
