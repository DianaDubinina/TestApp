package searchtickets.app.presentation.ui.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {
    fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd MMM, EEE", Locale.getDefault())
        return dateFormat.format(currentDate)
    }
}