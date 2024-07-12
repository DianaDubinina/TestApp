package searchtickets.app.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flights(
    val id: Int,
    val title: String,
    val time_range: String,
    val price: Int,
) : Parcelable
