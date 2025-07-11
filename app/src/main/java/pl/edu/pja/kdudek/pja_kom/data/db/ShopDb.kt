package pl.edu.pja.kdudek.pja_kom.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.edu.pja.kdudek.pja_kom.data.db.dao.CartDao
import pl.edu.pja.kdudek.pja_kom.data.db.dao.ProductDao
import pl.edu.pja.kdudek.pja_kom.data.db.entity.CartItemEntity
import pl.edu.pja.kdudek.pja_kom.data.db.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, CartItemEntity::class],
    version = 1
)
abstract class ShopDb : RoomDatabase() {
    abstract val cart: CartDao
    abstract val products: ProductDao
}
