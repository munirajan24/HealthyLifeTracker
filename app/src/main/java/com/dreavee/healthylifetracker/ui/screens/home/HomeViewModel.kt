package com.dreavee.healthylifetracker.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.data.local.entity.WaterSource
import com.dreavee.healthylifetracker.data.repository.SettingsRepository
import com.dreavee.healthylifetracker.data.repository.WaterRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class HomeUiState(
    val waterTotalMl: Int = 0,
    val waterGoalMl: Int = 2100,
    val waterPercentage: Float = 0f
)

class HomeViewModel(
    private val waterRepository: WaterRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            combine(
                waterRepository.getTotalForToday(),
                settingsRepository.getSettings()
            ) { total, settings ->
                val waterTotal = total ?: 0
                val goal = settings.waterGoalMl
                HomeUiState(
                    waterTotalMl = waterTotal,
                    waterGoalMl = goal,
                    waterPercentage = (waterTotal.toFloat() / goal.toFloat()).coerceIn(0f, 1f)
                )
            }.collect {
                _uiState.value = it
            }
        }
    }
    
    fun addWater(amountMl: Int) {
        viewModelScope.launch {
            waterRepository.addWaterIntake(amountMl, WaterSource.CUSTOM)
        }
    }
}
