package com.products.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.login.model.ProductScreenUI
import com.login.model.ProductUI
import com.products.dto.Product
import com.products.dto.ProductResponseDTO
import com.products.dto.category.ProductCategoryResponseDTO
import com.products.mapper.toCategoryList
import com.products.mapper.toProduct
import com.products.mapper.toProductList
import com.products.model.SingleProductScreenUI
import com.products.repo.ProductsRepo
import com.redux.UiFavouriteUpdater
import com.redux.UiProductInCartUpdater
import com.utils.network.ApiResponse
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val store: Store<ApplicationState>,
    private val productRepo: ProductsRepo,
    private val cardUpdated: UiProductInCartUpdater,
    private val favUpdater: UiFavouriteUpdater,
) : ViewModel() {


    private val productQueryMap: HashMap<String, Any> = HashMap()

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        getProducts()
        fetchCategory()
    }


    fun getProducts() {
        isLoading.value = true
        productQueryMap["limit"] = 1000
        viewModelScope.launch {
            when (val result = productRepo.fetchProducts(productQueryMap)) {
                is ApiResponse.Success<*> -> {
                    isLoading.value = false
                    val productList = result.responseData as ProductResponseDTO
                    print(" products $productList")

                    store.update {
                        return@update it.copy(
                            productList = productList.toProductList()
                        )
                    }
                }

                is ApiResponse.Failed<*> -> {
                    isLoading.value = false
                    print("Failed ro get products ${result.errorMsg}")
                }
            }
        }
    }


    private fun fetchCategory() {
        viewModelScope.launch {
            when (val result = productRepo.fetchCategory()) {
                is ApiResponse.Success<*> -> {
                    val categoryList = result.responseData as ProductCategoryResponseDTO
                    store.update {
                        return@update it.copy(
                            productCategory = categoryList.toCategoryList()
                        )
                    }
                }

                is ApiResponse.Failed<*> -> {
                    print("Failed ro get products ${result.errorMsg}")
                }
            }

        }
    }

    fun updateCart(id: Int) {
        viewModelScope.launch {
            store.update {
                cardUpdated.update(id, it)
            }
        }
    }


    fun fetchProductFromId(id: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val result = productRepo.fetchProducts(id.toString())) {
                is ApiResponse.Success<*> -> {
                    isLoading.value = false
                    val product = result.responseData as Product
                    val allProducts = store.stateFlow.value.productList
                    store.update {
                        return@update it.copy(
                            product = product.toProduct(),
                            relatedProducts = allProducts.filter { prod ->
                                prod.category == product.category
                                        && prod.id != product.id
                            }
                        )
                    }
                }

                is ApiResponse.Failed<*> -> {
                    isLoading.value = false
                    Log.d("TAG", "fetchProductFromIdError: ${result.errorMsg}")
                }
            }
        }
    }

    fun updateFavourite(id: Int) {
        viewModelScope.launch {
            store.update {
                favUpdater.update(id, it)
            }
        }
    }

    fun filterProduct(slug: String) {
        viewModelScope.launch {
            val selectedValue = store.stateFlow.value.selectedCategoryForHome
            val newValue = if (selectedValue == slug) "" else slug

            store.update {
                return@update it.copy(
                    selectedCategoryForHome = newValue
                )
            }
        }
    }

    val products = combine(
        store.stateFlow.map { it.productList },
        store.stateFlow.map { it.cartIds },
        store.stateFlow.map { it.productCategory },
        store.stateFlow.map { it.selectedCategoryForHome },
    ) { productList, inCartIds, category, categoryForHome ->
        val productUi = if (categoryForHome.isNotEmpty()) {
            productList.filter { it.category == categoryForHome }.map {
                ProductUI(
                    product = it,
                    inCart = inCartIds.contains(it.id)
                )
            }
        } else {
            productList.map {
                ProductUI(
                    product = it,
                    inCart = inCartIds.contains(it.id)
                )
            }
        }


        ProductScreenUI(
            productUI = productUi,
            categoryList = category,
            selectedCategory = categoryForHome
        )

    }


    val singleProduct = combine(
        store.stateFlow.map { it.product },
        store.stateFlow.map { it.relatedProducts },
        store.stateFlow.map { it.cartIds },
        store.stateFlow.map { it.favProductIds },

        ) { singleProduct, relatedProduct, cartIds, favProductIds ->
        SingleProductScreenUI(
            relatedProducts = relatedProduct,
            product = singleProduct,
            inCartStatus = cartIds.contains(singleProduct?.id),
            isFav = favProductIds.contains(singleProduct?.id)
        )
    }
}