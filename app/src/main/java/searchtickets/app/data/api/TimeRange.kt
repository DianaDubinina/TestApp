package searchtickets.app.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeRange(
    @field:Json(name = "") val value: String
)