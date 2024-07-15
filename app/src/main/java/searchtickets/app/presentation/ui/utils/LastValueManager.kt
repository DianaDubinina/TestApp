package searchtickets.app.presentation.ui.utils

import android.content.Context

object LastValueManager {
    private const val SHARED_PREFS_NAME = "app_prefs"
    private const val LAST_ARRIVAL_VALUE_KEY = "last_arrival_value"
    private const val LAST_DEPARTURE_VALUE_KEY = "last_departure_value"
    private const val DEFAULT_DEPARTURE_VALUE = "Москва"
    private const val DEFAULT_ARRIVAL_VALUE = "Coчи"

    fun saveLastArrivalValue(context: Context, value: String) {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        sharedPrefs.edit().putString(LAST_ARRIVAL_VALUE_KEY, value).apply()
    }

    fun saveLastDepartureValue(context: Context, value: String) {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        sharedPrefs.edit().putString(LAST_DEPARTURE_VALUE_KEY, value).apply()
    }

    fun getLastDepartureValue(context: Context): String {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPrefs.getString(LAST_DEPARTURE_VALUE_KEY, DEFAULT_DEPARTURE_VALUE)
            ?: DEFAULT_DEPARTURE_VALUE
    }

    fun getLastArrivalValue(context: Context): String {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPrefs.getString(LAST_ARRIVAL_VALUE_KEY, DEFAULT_ARRIVAL_VALUE)
            ?: DEFAULT_ARRIVAL_VALUE
    }
}