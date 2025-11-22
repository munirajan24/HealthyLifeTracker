package com.dreavee.healthylifetracker.data.repository

import com.dreavee.healthylifetracker.data.local.dao.UserSettingsDao
import com.dreavee.healthylifetracker.data.local.entity.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val userSettingsDao: UserSettingsDao) {
    
    fun getSettings(): Flow<UserSettings> {
        return userSettingsDao.getSettings().map { it ?: UserSettings() }
    }
    
    suspend fun getSettingsOnce(): UserSettings {
        return userSettingsDao.getSettingsOnce() ?: UserSettings()
    }
    
    suspend fun updateSettings(settings: UserSettings) {
        userSettingsDao.insertSettings(settings)
    }
    
    suspend fun updateWaterGoal(goalMl: Int) {
        val current = getSettingsOnce()
        userSettingsDao.updateSettings(current.copy(waterGoalMl = goalMl))
    }
}
