package searchtickets.app.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import searchtickets.app.domain.models.Arrival
import searchtickets.app.domain.models.Departure

@JsonClass(generateAdapter = true)
data class ApiListOfAll(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "badge") val badge: String?,
    @field:Json(name = "company") val company: String,
    @field:Json(name = "departure") val departure: Departure,
    @field:Json(name = "arrival") val arrival: Arrival,
    @field:Json(name = "price") val price: Price,
    @field:Json(name = "has_transfer") val has_transfer: Boolean
)

