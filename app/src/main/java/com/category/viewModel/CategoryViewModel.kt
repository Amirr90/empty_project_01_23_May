package com.category.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.category.ui.CategoryScreenUI
import com.category.ui.CategoryUI
import com.products.dto.ProductResponseDTO
import com.products.mapper.toProductList
import com.products.repo.ProductsRepo
import com.utils.network.ApiResponse
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val store: Store<ApplicationState>,
    private val productRepo: ProductsRepo,
) : ViewModel() {
    fun updateSelectedCategory(id: Int) {
        viewModelScope.launch {
            store.update {
                return@update it.copy(selectedCategoryIds = setOf(id))
            }
        }
    }


    val categoryUI = combine(
        store.stateFlow.map { it.productCategory },
        store.stateFlow.map { it.selectedCategoryIds },
        store.stateFlow.map { it.productByCategory },
    ) { category, selectedCategoryIds, productByCategory ->
        val cat = category.map {
            CategoryUI(
                category = it,
                isSelected = selectedCategoryIds.contains(it.id)
            )
        }
        CategoryScreenUI(
            categoryUI = cat,
            productByCategory = productByCategory
        )
    }

    fun fetchProductByCategory(catId: String) {
        viewModelScope.launch {
            when (val result = productRepo.fetchProductBrCategory(catId)) {
                is ApiResponse.Success<*> -> {
                    val productData = result.responseData as ProductResponseDTO
                    store.update {
                        return@update it.copy(
                            productByCategory = productData.toProductList()
                        )
                    }
                }

                is ApiResponse.Failed<*> -> {
                    Log.d("TAG", "fetchProductByCategoryError: ${result.errorMsg}")
                }
            }
        }
    }


}
