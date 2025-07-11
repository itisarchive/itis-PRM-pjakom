package pl.edu.pja.kdudek.pja_kom.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.edu.pja.kdudek.pja_kom.data.db.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert
    suspend fun addProducts(vararg product: ProductEntity)

    @Query("SELECT * FROM product;")
    suspend fun getProducts(): List<ProductEntity>
}
