package searchtickets.app.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val id: Int,
    val title: String,
    val town: String,
    val price: Int,
) : Parcelable
