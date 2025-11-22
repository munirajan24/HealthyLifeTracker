package com.dreavee.healthylifetracker.data.repository

import com.dreavee.healthylifetracker.data.local.datastore.PreferencesDataSource
import com.dreavee.healthylifetracker.domain.model.UserSettings
import com.dreavee.healthylifetracker.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val preferencesDataSource: PreferencesDataSource
) : SettingsRepository {
    
    override fun observeSettings(): Flow<UserSettings> {
        return preferencesDataSource.userSettings
    }
    
    override suspend fun updateSettings(update: (UserSettings) -> UserSettings) {
        preferencesDataSource.updateSettings(update)
    }
}
