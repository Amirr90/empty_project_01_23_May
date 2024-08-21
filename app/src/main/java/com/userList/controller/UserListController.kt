package com.userList.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.userList.model.User

class UserListController : TypedEpoxyController<List<User>>() {
    override fun buildModels(userData: List<User>?) {
        when {
            userData == null -> return
            userData.isEmpty() -> NoDataEpoxyModel().id(
                System.currentTimeMillis()
            ).addTo(this)

            else -> {
                userData.forEach {
                    UserEpoxyModel(it).id(
                        it.id
                    ).addTo(this)
                }

            }

        }

    }
}

