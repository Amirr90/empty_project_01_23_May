package com.favourite.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cart.controller.CartProductController
import com.cart.viewModel.CartViewModel
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.FragmentCartScreenBinding
import com.emptyprojectt1.databinding.FragmentFavouriteProductScreenBinding
import com.favourite.controller.FavouriteProductController
import com.favourite.viewModel.FavouriteProductViewModel
import com.navigationController.navigateToProductDetailScreen
import com.products.controller.SingleProductDetailEpoxyModel
import com.products.listeners.ProductClickListener
import com.products.model.Product
import com.products.viewModel.ProductViewModel
import com.utils.flowCollector.collectFlow
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouriteProductScreen : Fragment(), ProductClickListener {

    private var _binding: FragmentFavouriteProductScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavouriteProductViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var controller: FavouriteProductController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteProductScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavouriteProductViewModel::class.java]
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = FavouriteProductController(this)
        binding.favouriteProductController.setController(controller)

        collectFlow(viewModel.favouriteProducts) {
            controller.setData(it)
        }
    }

    override fun onProductItemClicked(product: Product) {
        navigateToProductDetailScreen(product)
    }

    override fun onCartIconClicked(id: Int) {
        productViewModel.updateCart(id)
    }
}