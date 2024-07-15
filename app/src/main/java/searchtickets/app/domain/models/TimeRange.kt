package searchtickets.app.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeRange(
    val time: String,
) : Parcelable
