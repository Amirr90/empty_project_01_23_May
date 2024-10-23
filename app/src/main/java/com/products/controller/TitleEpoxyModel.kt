package com.products.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ProductTitleViewBinding
import com.emptyprojectt1.databinding.SearchBarBinding
import com.emptyprojectt1.databinding.TitleViewBinding
import com.emptyprojectt1.databinding.TopHeaderViewBinding


data class TitleEpoxyModel(
    val text: String,
) : ViewBindingKotlinModel<TitleViewBinding>(R.layout.title_view) {
    override fun TitleViewBinding.bind() {
        title = text
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}

data class ProductTitleEpoxyModel(
    val text: String,
    val viewAllClick: () -> Unit
) : ViewBindingKotlinModel<ProductTitleViewBinding>(R.layout.product_title_view) {
    override fun ProductTitleViewBinding.bind() {
        title = text
        tvViewAll.setOnClickListener {
            viewAllClick.invoke()
        }
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}

data class TopBarEpoxyModel(
    val text: String,
) : ViewBindingKotlinModel<TopHeaderViewBinding>(R.layout.top_header_view) {
    override fun TopHeaderViewBinding.bind() {

    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}

data class SearchBarEpoxyModel(
    val text: String,
) : ViewBindingKotlinModel<SearchBarBinding>(R.layout.search_bar) {
    override fun SearchBarBinding.bind() {

    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}