package com.dreavee.healthylifetracker.domain.repository

import com.dreavee.healthylifetracker.domain.model.UserSettings
import kotlinx.coroutines.flow.Flow

/**
 * Repository for user settings and preferences
 * Single source of truth for all app configuration
 */
interface SettingsRepository {
    
    /**
     * Observe user settings as a reactive stream
     * Emits whenever settings change
     */
    fun observeSettings(): Flow<UserSettings>
    
    /**
     * Update user settings
     */
    suspend fun updateSettings(update: (UserSettings) -> UserSettings)
}
