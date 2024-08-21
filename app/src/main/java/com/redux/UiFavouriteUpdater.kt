package com.redux

import com.utils.redux.ApplicationState
import javax.inject.Inject

class UiFavouriteUpdater @Inject constructor() {

    fun update(productId: Int, currentState: ApplicationState): ApplicationState {
        val currentProductIdsInCart = currentState.favProductIds
        val newProductIdsInCart = if (currentProductIdsInCart.contains(productId)) {
            currentProductIdsInCart - productId
        } else {
            currentProductIdsInCart + productId
        }
        return currentState.copy(favProductIds = newProductIdsInCart)
    }
}
