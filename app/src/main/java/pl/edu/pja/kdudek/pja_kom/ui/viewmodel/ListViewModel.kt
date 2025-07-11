package pl.edu.pja.kdudek.pja_kom.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.edu.pja.kdudek.pja_kom.data.ProductRepository
import pl.edu.pja.kdudek.pja_kom.model.Product
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    var state = MutableStateFlow(listOf<Product>())

    fun load() {
        state.update { repository.products }
        viewModelScope.launch {
            repository.initDb()
        }
    }
}
