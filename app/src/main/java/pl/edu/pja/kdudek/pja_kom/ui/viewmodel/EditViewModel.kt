package pl.edu.pja.kdudek.pja_kom.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.edu.pja.kdudek.pja_kom.data.CartRepository
import pl.edu.pja.kdudek.pja_kom.data.ProductRepository
import pl.edu.pja.kdudek.pja_kom.model.Product
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    var state = MutableStateFlow<Product?>(null)

    fun load(id: Int) {
        state.update { productRepository.findById(id) }
    }

    fun addToCart() {
        state.value?.let {
            viewModelScope.launch {
                cartRepository.addProduct(it)
            }
        }
    }
}
