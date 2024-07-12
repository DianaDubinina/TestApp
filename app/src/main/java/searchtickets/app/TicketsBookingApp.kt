package searchtickets.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TicketsBookingApp : Application() {
    companion object {
        private lateinit var instance: TicketsBookingApp

        fun getAppContext(): TicketsBookingApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}