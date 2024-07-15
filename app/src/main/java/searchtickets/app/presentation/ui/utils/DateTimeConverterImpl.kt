package searchtickets.app.presentation.ui.utils

import java.text.SimpleDateFormat
import java.util.Locale

class DateTimeConverterImpl : DateTimeConverter {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    private val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    override fun convertDateTime(dateTimeString: String): String {
        val date = inputFormat.parse(dateTimeString)
        return outputFormat.format(date)
    }
}