package com.dreavee.healthylifetracker.di

import android.content.Context
import com.dreavee.healthylifetracker.data.local.HealthTrackerDatabase
import com.dreavee.healthylifetracker.data.local.datastore.PreferencesDataSource
import com.dreavee.healthylifetracker.data.repository.SettingsRepositoryImpl
import com.dreavee.healthylifetracker.data.repository.WaterRepository
import com.dreavee.healthylifetracker.domain.repository.SettingsRepository

/**
 * Manual DI container for the app
 */
class AppContainer(private val context: Context) {

    // Database
    val database: HealthTrackerDatabase by lazy {
        HealthTrackerDatabase.getDatabase(context)
    }

    // DataStore
    private val preferencesDataSource: PreferencesDataSource by lazy {
        PreferencesDataSource(context)
    }

    // Repositories
    val settingsRepository: SettingsRepository by lazy {
        SettingsRepositoryImpl(preferencesDataSource)
    }
    
    val waterRepository: WaterRepository by lazy {
        WaterRepository(database.waterIntakeDao())
    }
}
