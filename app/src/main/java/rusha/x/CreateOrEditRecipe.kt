package rusha.x

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrEditRecipe(
    val id: Int? = null,
    val name: String,
    val description: String,
    val ingredients: List<Ingredient>
) {
    @Serializable
    data class Ingredient(
        val countInRecipe: Double,
        val product: CreateOrEditProductByName
    ) {
        fun withIncrementCount(): Ingredient {
            return copy(
                countInRecipe = countInRecipe + 1
            )
        }

        fun withDecrementCount(): Ingredient {
            return copy(
                countInRecipe = (countInRecipe - 1).coerceAtLeast(minimumValue = 0.0)
            )
        }

        fun withCount(count: Double): Ingredient {
            return copy(
                countInRecipe = count
            )
        }
    }
}

@Serializable
data class CreateOrEditProductByName(
    val id: Int? = null,
    val name: String,
    val price: Double,
    val unit: String
)