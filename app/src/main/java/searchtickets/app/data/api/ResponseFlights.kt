package searchtickets.app.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseFlights(
    @field:Json(name = "tickets_offers") val offers: List<ApiListOfFlights>
)
