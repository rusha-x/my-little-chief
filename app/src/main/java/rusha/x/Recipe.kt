package rusha.x

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val ingredients: List<Ingredient>
) : Parcelable {

    @Parcelize
    @Serializable
    data class Ingredient(
        val countInRecipe: Double,
        val product: Product
    ) : Parcelable
}