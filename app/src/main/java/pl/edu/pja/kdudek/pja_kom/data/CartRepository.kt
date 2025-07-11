package pl.edu.pja.kdudek.pja_kom.data

import pl.edu.pja.kdudek.pja_kom.model.CartItem
import pl.edu.pja.kdudek.pja_kom.model.Product

interface CartRepository {
    suspend fun getCartItems(): Pair<List<CartItem>, Double>
    suspend fun addProduct(product: Product)
    suspend fun removeOneProduct(product: Product)
    suspend fun deleteAllProducts(product: Product)
    suspend fun clear()
}
