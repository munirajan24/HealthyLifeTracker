package com.dreavee.healthylifetracker.di

import android.content.Context
import com.dreavee.healthylifetracker.data.local.HealthTrackerDatabase
import com.dreavee.healthylifetracker.data.repository.SettingsRepository
import com.dreavee.healthylifetracker.data.repository.WaterRepository

/**
 * Manual DI container for the app
 */
class AppContainer(private val context: Context) {

    // Database
    val database: HealthTrackerDatabase by lazy {
        HealthTrackerDatabase.getDatabase(context)
    }

    // Repositories
    val waterRepository: WaterRepository by lazy {
        WaterRepository(database.waterIntakeDao())
    }
    
    val settingsRepository: SettingsRepository by lazy {
        SettingsRepository(database.userSettingsDao())
    }
}
