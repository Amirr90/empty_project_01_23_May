package com.userList.controller

import com.amir.quran.utils.epoxy.ViewBindingKotlinModel
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.UserListViewBinding
import com.userList.model.User

data class UserEpoxyModel(
    val userData: User
) : ViewBindingKotlinModel<UserListViewBinding>(R.layout.user_list_view) {
    override fun UserListViewBinding.bind() {
        user = userData
    }
}