package com.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.category.controller.CategoryScreenController
import com.category.controller.ProductByCategoryController
import com.category.viewModel.CategoryViewModel
import com.emptyprojectt1.databinding.FragmentCategoryScreenBinding
import com.navigationController.navigateToProductDetailScreen
import com.products.listeners.ProductClickListener
import com.products.model.Product
import com.utils.flowCollector.collectFlow
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryScreen : Fragment(), ProductClickListener {

    private var _binding: FragmentCategoryScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CategoryViewModel
    private lateinit var controller: CategoryScreenController
    private lateinit var productByCategoryController: ProductByCategoryController

    @Inject
    lateinit var store: Store<ApplicationState>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        controller = CategoryScreenController(viewModel)
        productByCategoryController = ProductByCategoryController(this)

        val catIds = store.stateFlow.value.productCategory
        if (catIds.isNotEmpty() && store.stateFlow.value.productByCategory.isEmpty()) {
            val catId = catIds.first().slug
            viewModel.fetchProductByCategory(catId)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            categoryListingController.setController(controller)
            categoryProductController.setController(productByCategoryController)
        }


        collectFlow(viewModel.categoryUI) {
            controller.setData(it)
            productByCategoryController.setData(it)
        }
    }


    override fun onProductItemClicked(product: Product) {
        navigateToProductDetailScreen(product)
    }
}