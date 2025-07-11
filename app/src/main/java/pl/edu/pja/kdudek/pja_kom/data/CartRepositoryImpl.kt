package pl.edu.pja.kdudek.pja_kom.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.edu.pja.kdudek.pja_kom.data.db.ShopDb
import pl.edu.pja.kdudek.pja_kom.data.db.entity.CartItemEntity
import pl.edu.pja.kdudek.pja_kom.model.CartItem
import pl.edu.pja.kdudek.pja_kom.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val shopDb: ShopDb
) : CartRepository {

    override suspend fun getCartItems(): Pair<List<CartItem>, Double> =
        withContext(Dispatchers.IO) {
            val cartItems = shopDb.cart.getCartProducts()
                .map { it.toDomain() }
            val totalPrice = cartItems.sumOf { it.product.price * it.quantity }
            return@withContext cartItems to totalPrice
        }

    override suspend fun addProduct(product: Product) = withContext(Dispatchers.IO) {
        val cartProduct = shopDb.cart.getCartProduct(product.id)
        if (cartProduct == null) {
            shopDb.cart.addToCart(CartItemEntity(product.id, 1))
        } else {
            shopDb.cart.updateCartItem(cartProduct.cartItem.copy(quantity = cartProduct.cartItem.quantity + 1))
        }
    }


    override suspend fun removeOneProduct(product: Product) {
//        val idx = _cartItems.indexOfFirst { it.product.id == product.id }
//        if (idx != -1) {
//            val current = _cartItems[idx]
//            if (current.quantity == 1) {
//                _cartItems.removeAt(idx)
//            } else {
//                _cartItems[idx] = current.copy(quantity = current.quantity - 1)
//            }
//        }
    }

    override suspend fun deleteAllProducts(product: Product) {
//        _cartItems.removeIf { it.product.id == product.id }
    }

    override suspend fun clear() {
//        _cartItems.clear()
    }
}
