package com.dreavee.healthylifetracker.ui.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.domain.logic.StreakData
import com.dreavee.healthylifetracker.domain.logic.StreakManager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CalendarViewModel(
    private val streakManager: StreakManager
) : ViewModel() {

    private val _streakData = MutableStateFlow<StreakData?>(null)
    val streakData: StateFlow<StreakData?> = _streakData.asStateFlow()

    init {
        loadStreaks()
    }

    fun loadStreaks() {
        viewModelScope.launch {
            _streakData.value = streakManager.calculateStreaks()
        }
    }
}
