package pl.edu.pja.kdudek.pja_kom.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItemEntity(
    @PrimaryKey
    val productId: Int,
    val quantity: Int,
)
