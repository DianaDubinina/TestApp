package searchtickets.app.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Departure(
    val town: String,
    val date: String,
    val airport: String,
) : Parcelable
