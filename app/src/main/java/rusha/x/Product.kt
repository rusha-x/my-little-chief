package rusha.x

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val unit: String
) : Parcelable