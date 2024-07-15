package searchtickets.app.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import searchtickets.app.data.api.Price

@Parcelize
data class All(
    val id: Int,
    val badge: String? = "",
    val company: String,
    val departure: Departure,
    val arrival: Arrival,
    val price: Price,
    val has_transfer: Boolean,
) : Parcelable
