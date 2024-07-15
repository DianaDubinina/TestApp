package searchtickets.app.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseAll(
    @field:Json(name = "tickets") val offers: List<ApiListOfAll>
)
