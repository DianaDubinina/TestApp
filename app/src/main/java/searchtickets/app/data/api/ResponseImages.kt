package searchtickets.app.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseImages(
    @field:Json(name = "offers") val offers: List<ApiListOfInfo>
)
