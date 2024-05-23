package com.userList.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.NoDataViewBinding
import com.emptyprojectt1.databinding.UserListViewBinding
import com.userList.model.User

data class NoDataEpoxyModel(
    val id: String =""
) : ViewBindingKotlinModel<NoDataViewBinding>(R.layout.no_data_view) {
    override fun NoDataViewBinding.bind() {

    }
}