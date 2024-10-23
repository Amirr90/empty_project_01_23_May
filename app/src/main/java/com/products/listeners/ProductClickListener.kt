package com.products.listeners

import com.products.model.Product

interface ProductClickListener {
    fun onCartIconClicked(id: Int) {}
    fun onProductItemClicked(product: Product) {}
    fun onViewAllClicked() {}
}