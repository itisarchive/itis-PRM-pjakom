package pl.edu.pja.kdudek.pja_kom.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.edu.pja.kdudek.pja_kom.data.db.ShopDb
import pl.edu.pja.kdudek.pja_kom.data.db.entity.ProductEntity
import pl.edu.pja.kdudek.pja_kom.model.Category
import pl.edu.pja.kdudek.pja_kom.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalProductRepository @Inject constructor(
    private val shopDb: ShopDb
) : ProductRepository {
    private val _products: MutableList<Product> = """
        |1, LAPTOP, "Laptop Ultrabook XYZ", 4999.99
        |2, SMARTPHONE, "Smartfon Galaxy S25", 3499.00
        |3, HEADPHONE, "Słuchawki Bezprzewodowe ANC Pro", 799.50
        |4, DISPLAY, "Monitor Gamingowy 27\" QHD", 1899.00
        |5, MOUSE, "Mysz Bezprzewodowa MX Master 5", 449.00
        |6, KEYBOARD, "Klawiatura Mechaniczna RGB", 599.00
        |7, DISK, "Dysk SSD NVMe 512GB", 299.00
        |8, DISK, "Dysk SSD NVMe 1TB", 399.00
        |9, DISK, "Dysk SSD NVMe 2TB", 499.00
        |10, DISK, "Dysk SSD NVMe 4TB", 599.00
        |11, DISK, "Dysk SSD NVMe 8TB", 699.00
     """.trimMargin("|")
        .split("\n")
        .filter { it.isNotBlank() }
        .map { line ->
            val (id, category, name, price) = line.split(", ")
            Product(
                id = id.toInt(),
                category = Category.valueOf(category),
                name = name.replace("\"", ""),
                price = price.toDouble()
            )
        }
        .toMutableList()

    override val products: List<Product>
        get() = _products

    override suspend fun initDb() = withContext(Dispatchers.IO) {
        if (shopDb.products.getProducts().isEmpty()) {
            shopDb.products.addProducts(
                *_products
                    .map { ProductEntity(it.id, it.category, it.name, it.price) }
                    .toTypedArray()
            )
        }
    }
}
