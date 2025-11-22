package com.dreavee.healthylifetracker.feature.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.domain.model.AppTheme
import com.dreavee.healthylifetracker.domain.model.Gender
import com.dreavee.healthylifetracker.domain.model.UserSettings
import com.dreavee.healthylifetracker.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    
    val settings: StateFlow<UserSettings> = settingsRepository.observeSettings()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserSettings()
        )
    
    fun updateName(name: String) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(name = name) }
        }
    }
    
    fun updateGender(gender: Gender) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(gender = gender) }
        }
    }
    
    fun updateWaterGoal(goalMl: Int) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(waterDailyGoalMl = goalMl) }
        }
    }
    
    fun updateQuickAddOption(index: Int, value: Int) {
        viewModelScope.launch {
            settingsRepository.updateSettings { settings ->
                val newOptions = settings.waterQuickAddOptions.toMutableList()
                if (index in newOptions.indices) {
                    newOptions[index] = value
                }
                settings.copy(waterQuickAddOptions = newOptions)
            }
        }
    }
    
    fun toggleWaterReminders(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(waterReminderEnabled = enabled) }
        }
    }
    
    fun toggleGymTracking(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(gymTrackingEnabled = enabled) }
        }
    }
    
    fun updateGymWeeklyTarget(target: Int) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(gymWeeklyTargetSessions = target) }
        }
    }
    
    fun toggleMealsTracking(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(mealsTrackingEnabled = enabled) }
        }
    }
    
    fun updateTheme(theme: AppTheme) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(theme = theme) }
        }
    }
    
    fun toggle24hFormat(use24h: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateSettings { it.copy(use24hFormat = use24h) }
        }
    }
}
