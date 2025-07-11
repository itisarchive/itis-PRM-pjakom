package pl.edu.pja.kdudek.pja_kom.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import pl.edu.pja.kdudek.pja_kom.data.db.entity.CartItemEntity
import pl.edu.pja.kdudek.pja_kom.data.db.entity.CartProduct

@Dao
interface CartDao {

    @Insert
    suspend fun addToCart(cartItemEntity: CartItemEntity)

    @Update
    suspend fun updateCartItem(cartItemEntity: CartItemEntity)

    @Transaction
    @Query("SELECT * FROM cart_item;")
    suspend fun getCartProducts(): List<CartProduct>

    @Transaction
    @Query("SELECT * FROM cart_item WHERE productId = :id")
    suspend fun getCartProduct(id: Int): CartProduct?
}
