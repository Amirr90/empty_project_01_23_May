package com.redux

import com.utils.redux.ApplicationState
import javax.inject.Inject

class UiProductInCartUpdater @Inject constructor() {

    fun update(productId: Int, currentState: ApplicationState): ApplicationState {
        val currentProductIdsInCart = currentState.cartIds
        val newProductIdsInCart = if (currentProductIdsInCart.contains(productId)) {
            currentProductIdsInCart - productId
        } else {
            currentProductIdsInCart + productId
        }
        return currentState.copy(cartIds = newProductIdsInCart)
    }
}
