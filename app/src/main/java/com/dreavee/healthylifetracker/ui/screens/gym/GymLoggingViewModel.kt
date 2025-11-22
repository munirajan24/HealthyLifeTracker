package com.dreavee.healthylifetracker.ui.screens.gym

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.data.database.entities.WorkoutLogEntity
import com.dreavee.healthylifetracker.data.repository.TrackerRepository

import kotlinx.coroutines.launch


class GymLoggingViewModel(
    private val repository: TrackerRepository
) : ViewModel() {

    fun logWorkout(type: String, durationMins: Int, calories: Int) {
        viewModelScope.launch {
            val endTime = System.currentTimeMillis()
            val startTime = endTime - (durationMins * 60 * 1000L)
            
            repository.logWorkout(
                WorkoutLogEntity(
                    startTime = startTime,
                    endTime = endTime,
                    type = type,
                    caloriesBurned = calories
                )
            )
        }
    }
}
