package com.varshakulkarni.dailynews

import android.app.Application
import com.airbnb.mvrx.Mavericks

class DailyNewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)
    }
}