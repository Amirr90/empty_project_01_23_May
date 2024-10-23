package com.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cart.viewModel.CartViewModel
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.FragmentProductListScreenBinding
import com.navigationController.navigateToProductDetailScreen
import com.products.controller.ProductScreenController
import com.products.listeners.ProductClickListener
import com.products.model.Product
import com.products.viewModel.ProductViewModel
import com.utils.flowCollector.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListScreen : Fragment(), ProductClickListener {
    private var _binding: FragmentProductListScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var controller: ProductScreenController


    val uniqueString = mutableSetOf("")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductListScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        controller = ProductScreenController(this, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productListController.setController(controller)
        observer()
    }

    private fun observer() {
        collectFlow(viewModel.products) {
            controller.setData(it)
        }

        collectFlow(viewModel.isLoading) {
            binding.progressBar5.isVisible = it
        }

    }

    override fun onCartIconClicked(id: Int) {
        viewModel.updateCart(id = id)
    }

    override fun onProductItemClicked(product: Product) {
        navigateToProductDetailScreen(product)
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    override fun onViewAllClicked() {
        findNavController().navigate(R.id.action_global_allProductListing)
    }
}