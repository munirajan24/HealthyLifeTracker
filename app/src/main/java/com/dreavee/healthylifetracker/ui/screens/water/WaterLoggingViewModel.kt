package com.dreavee.healthylifetracker.ui.screens.water

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.data.database.entities.WaterLogEntity
import com.dreavee.healthylifetracker.data.repository.TrackerRepository
import com.dreavee.healthylifetracker.domain.logic.TimeRuleEngine

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class WaterLoggingViewModel(
    private val repository: TrackerRepository,
    private val timeRuleEngine: TimeRuleEngine,
    private val notificationHelper: com.dreavee.healthylifetracker.util.NotificationHelper
) : ViewModel() {

    private val _canDrink = MutableStateFlow(true)
    val canDrink: StateFlow<Boolean> = _canDrink.asStateFlow()

    private val _nextSafeTime = MutableStateFlow(0L)
    val nextSafeTime: StateFlow<Long> = _nextSafeTime.asStateFlow()

    init {
        checkWaterStatus()
    }

    fun checkWaterStatus() {
        viewModelScope.launch {
            val lastMeal = repository.getLastMeal()
            val canDrinkNow = timeRuleEngine.canDrinkWater(System.currentTimeMillis(), lastMeal)
            _canDrink.value = canDrinkNow
            if (!canDrinkNow) {
                _nextSafeTime.value = timeRuleEngine.getNextSafeWaterTime(lastMeal)
            }
        }
    }

    fun logWater(amountMl: Int) {
        viewModelScope.launch {
            if (_canDrink.value) {
                repository.logWater(
                    WaterLogEntity(
                        timestamp = System.currentTimeMillis(),
                        amountMl = amountMl
                    )
                )
            }
        }
    }
}
