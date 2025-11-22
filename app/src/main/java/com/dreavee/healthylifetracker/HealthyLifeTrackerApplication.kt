package com.dreavee.healthylifetracker

import android.app.Application
import com.dreavee.healthylifetracker.di.AppContainer

class HealthyLifeTrackerApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
