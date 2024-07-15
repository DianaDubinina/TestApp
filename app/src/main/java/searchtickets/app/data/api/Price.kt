package searchtickets.app.data.api

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize

@JsonClass(generateAdapter = true)
data class Price(
    @field:Json(name = "value") val value: Int
) : Parcelable