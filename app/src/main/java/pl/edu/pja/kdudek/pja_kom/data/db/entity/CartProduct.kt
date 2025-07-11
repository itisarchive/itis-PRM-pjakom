package pl.edu.pja.kdudek.pja_kom.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation
import pl.edu.pja.kdudek.pja_kom.model.CartItem

data class CartProduct(
    @Embedded
    val cartItem: CartItemEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: ProductEntity
) {
    fun toDomain(): CartItem {
        return CartItem(
            product = product.toDomain(),
            quantity = cartItem.quantity
        )
    }
}
