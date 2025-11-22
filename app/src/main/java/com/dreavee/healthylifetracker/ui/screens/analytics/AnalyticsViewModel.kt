package com.dreavee.healthylifetracker.ui.screens.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.data.repository.TrackerRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar


class AnalyticsViewModel(
    private val repository: TrackerRepository
) : ViewModel() {

    private val _weeklyWaterData = MutableStateFlow<List<Float>>(emptyList())
    val weeklyWaterData: StateFlow<List<Float>> = _weeklyWaterData.asStateFlow()

    init {
        loadWeeklyData()
    }

    private fun loadWeeklyData() {
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            // Reset to end of today
            calendar.set(Calendar.HOUR_OF_DAY, 23)
            calendar.set(Calendar.MINUTE, 59)
            calendar.set(Calendar.SECOND, 59)
            val endTime = calendar.timeInMillis

            // Go back 6 days to get a 7-day window
            calendar.add(Calendar.DAY_OF_YEAR, -6)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            val startTime = calendar.timeInMillis

            val logs = repository.getWaterLogsForDay(startTime, endTime).first()
            
            // Aggregate by day
            val dailyTotals = FloatArray(7) { 0f }
            
            logs.forEach { log ->
                val logCal = Calendar.getInstance().apply { timeInMillis = log.timestamp }
                // Calculate day index relative to start time (0 to 6)
                val dayDiff = ((log.timestamp - startTime) / (24 * 60 * 60 * 1000)).toInt()
                if (dayDiff in 0..6) {
                    dailyTotals[dayDiff] += log.amountMl.toFloat()
                }
            }
            
            _weeklyWaterData.value = dailyTotals.toList()
        }
    }
}
