package rusha.x

import kotlinx.serialization.Serializable

@Serializable
data class Basket(
    val id: String,
    val items: List<Item>
) {
    @Serializable
    data class Item(
        val count: Double,
        val product: Product
    )
}