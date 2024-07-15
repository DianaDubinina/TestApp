package searchtickets.app.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Arrival(
    @field:Json(name = "town") val town: String,
    @field:Json(name = "date") val date: String,
    @field:Json(name = "airport") val airport: String,
)