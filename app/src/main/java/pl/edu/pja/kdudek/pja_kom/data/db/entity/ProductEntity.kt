package pl.edu.pja.kdudek.pja_kom.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.edu.pja.kdudek.pja_kom.model.Category
import pl.edu.pja.kdudek.pja_kom.model.Product

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: Category,
    val name: String,
    val price: Double
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            category = category,
            name = name,
            price = price
        )
    }
}
