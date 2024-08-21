package com.products.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.emptyprojectt1.databinding.FragmentSingleProductDetailScreenBinding
import com.navigationController.navigateToCartScreen
import com.navigationController.navigateToProductDetailScreen
import com.products.controller.SingleProductScreenController
import com.products.listeners.OnFavouriteClickListener
import com.products.listeners.ProductClickListener
import com.products.model.Product
import com.products.model.SingleProductScreenUI
import com.products.viewModel.ProductViewModel
import com.utils.flowCollector.collectFlow
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SingleProductDetailScreen : Fragment(), ProductClickListener, OnFavouriteClickListener {
    private var _binding: FragmentSingleProductDetailScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var controller: SingleProductScreenController
    lateinit var viewModel: ProductViewModel

    @Inject
    lateinit var store: Store<ApplicationState>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleProductDetailScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        controller = SingleProductScreenController(this, this) {
            navigateToCartScreen()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            singleProductDetailController.setController(controller)
        }

        val product = arguments?.let { SingleProductDetailScreenArgs.fromBundle(it).product }
        product?.let {
            updateProductUI(it)
            viewModel.fetchProductFromId(it.id)
        }


        collectFlow(viewModel.singleProduct) {
            controller.setData(it)
        }

        collectFlow(viewModel.isLoading) {
            binding.progressBar4.isVisible = it
        }

    }

    private fun updateProductUI(it: Product) {
        controller.setData(
            SingleProductScreenUI(
                product = it,
            )
        )
    }

    override fun onCartIconClicked(id: Int) {
        viewModel.updateCart(id)
    }

    override fun onProductItemClicked(product: Product) {
        navigateToProductDetailScreen(product)
    }

    override fun onFavouriteIconClick(productId: Int) {
        viewModel.updateFavourite(productId)
    }
}