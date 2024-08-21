package com.navigationController

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emptyprojectt1.R
import com.products.model.Product
import com.products.ui.SingleProductDetailScreenDirections


fun Fragment.navigateToProductDetailScreen(product: Product) {
    Log.d("TAG", "navigateToProductDetailScreen: $product")
    val action = SingleProductDetailScreenDirections.actionGlobalSingleProductDetailScreen(product)
    findNavController().navigate(action)

}

fun Fragment.navigateToCartScreen() {
    Log.d("TAG", "navigateToCartScreen:")
    findNavController().navigate(R.id.action_global_cartScreen)

}