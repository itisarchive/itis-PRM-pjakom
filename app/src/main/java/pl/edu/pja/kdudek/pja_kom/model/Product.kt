package pl.edu.pja.kdudek.pja_kom.model

data class Product(
    val id: Int,
    val category: Category,
    val name: String,
    val price: Double
)

val previewData = List(10) {
    Product(
        id = it,
        category = Category.entries.random(),
        name = "Product $it",
        price = (100..5000).random().toDouble()
    )
}
