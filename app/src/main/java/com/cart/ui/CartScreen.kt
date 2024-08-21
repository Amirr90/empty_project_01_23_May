package com.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cart.controller.CartProductController
import com.cart.viewModel.CartViewModel
import com.emptyprojectt1.databinding.FragmentCartScreenBinding
import com.navigationController.navigateToProductDetailScreen
import com.products.listeners.ProductClickListener
import com.products.mapper.appendDollar
import com.products.model.Product
import com.products.viewModel.ProductViewModel
import com.utils.flowCollector.collectFlow
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartScreen : Fragment(), ProductClickListener {

    private var _binding: FragmentCartScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CartViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var cartController: CartProductController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        cartController = CartProductController(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.cartEpoxyController.setController(controller = cartController)

        collectFlow(viewModel.productInCart) {
            cartController.setData(it)
            binding.apply {
                cart = it
                textView16.text = it.totalAmount.appendDollar()
            }

        }
    }

    override fun onCartIconClicked(id: Int) {
        productViewModel.updateCart(id)
    }

    override fun onProductItemClicked(product: Product) {
        navigateToProductDetailScreen(
            product
        )
    }
}