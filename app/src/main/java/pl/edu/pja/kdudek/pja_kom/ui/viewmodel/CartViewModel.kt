package pl.edu.pja.kdudek.pja_kom.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.edu.pja.kdudek.pja_kom.data.CartRepository
import pl.edu.pja.kdudek.pja_kom.model.Product
import pl.edu.pja.kdudek.pja_kom.ui.model.CartUiState
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    val state = MutableStateFlow(CartUiState(emptyList(), 0.0))

    fun load() {
        viewModelScope.launch {
            val data = cartRepository.getCartItems()
            state.update {
                CartUiState(
                    products = data.first,
                    totalPrice = data.second
                )
            }
        }
    }

    fun onAdd(product: Product) {
        viewModelScope.launch {
            cartRepository.addProduct(product)
            load()
        }
    }

    fun onRemove(product: Product) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                cartRepository.removeOneProduct(product)
            }
            load()
        }
    }

    fun onDelete(product: Product) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                cartRepository.deleteAllProducts(product)
            }
            load()
        }
    }

    fun onCartClear() {
        state.value = CartUiState(emptyList(), 0.0)
    }
}
