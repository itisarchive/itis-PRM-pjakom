package pl.edu.pja.kdudek.pja_kom.data

import pl.edu.pja.kdudek.pja_kom.model.Product

interface ProductRepository {
    val products: List<Product>

    suspend fun initDb()

    fun findById(id: Int): Product? {
        return products.find { it.id == id }
    }
}
