package com.dreavee.healthylifetracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dreavee.healthylifetracker.di.AppContainer
import com.dreavee.healthylifetracker.feature.settings.viewmodel.SettingsViewModel
import com.dreavee.healthylifetracker.ui.screens.home.HomeViewModel

class ViewModelFactory(private val appContainer: AppContainer) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(
                    waterRepository = appContainer.waterRepository,
                    settingsRepository = appContainer.settingsRepository
                ) as T
            }
            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> {
                SettingsViewModel(
                    settingsRepository = appContainer.settingsRepository
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
