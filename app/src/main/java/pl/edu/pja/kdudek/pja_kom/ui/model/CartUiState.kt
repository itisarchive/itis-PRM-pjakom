package pl.edu.pja.kdudek.pja_kom.ui.model

import pl.edu.pja.kdudek.pja_kom.model.CartItem

data class CartUiState(
    val products: List<CartItem>,
    val totalPrice: Double,
)
