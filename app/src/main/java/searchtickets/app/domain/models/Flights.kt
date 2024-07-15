package searchtickets.app.domain.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flights(
    val id: Int,
    val title: String,
    @field:Json(name = "time_range") val timeRange: List<String>,
    val price: Int,
) : Parcelable
