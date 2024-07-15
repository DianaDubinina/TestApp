package searchtickets.app.presentation.ui.utils

import android.app.DatePickerDialog
import android.content.Context
import com.google.android.material.button.MaterialButton
import java.util.Calendar

class DatePickerManager(
    private val context: Context,
    private val dateDeparture: MaterialButton,
    private val dateReturn: MaterialButton
) {
    fun setupDatePicker() {
        setupDatePickerForButton(dateDeparture)
        setupDatePickerForButton(dateReturn)
    }

    private fun setupDatePickerForButton(button: MaterialButton) {
        val currentDate = DateUtils.getCurrentDate()
        button.text = currentDate

        button.setOnClickListener {
            showDatePickerDialog(button)
        }
    }

    private fun showDatePickerDialog(button: MaterialButton) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay ${getMonthName(selectedMonth)}, ${
                    getDayOfWeek(
                        selectedDay,
                        selectedMonth,
                        selectedYear
                    )
                }"
                if (button == dateDeparture) {
                    dateDeparture.text = selectedDate
                } else {
                    dateReturn.text = button.text // Keep the previous date for dateReturn
                }
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun getMonthName(month: Int): String {
        return when (month) {
            0 -> "янв"
            1 -> "фев"
            2 -> "мар"
            3 -> "апр"
            4 -> "май"
            5 -> "июн"
            6 -> "июл"
            7 -> "авг"
            8 -> "сен"
            9 -> "окт"
            10 -> "ноя"
            11 -> "дек"
            else -> ""
        }
    }

    private fun getDayOfWeek(day: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "пн"
            Calendar.TUESDAY -> "вт"
            Calendar.WEDNESDAY -> "ср"
            Calendar.THURSDAY -> "чт"
            Calendar.FRIDAY -> "пт"
            Calendar.SATURDAY -> "сб"
            Calendar.SUNDAY -> "вс"
            else -> ""
        }
    }

    fun initDateBackButton() {
        dateReturn.text = "обратно"
    }
}
