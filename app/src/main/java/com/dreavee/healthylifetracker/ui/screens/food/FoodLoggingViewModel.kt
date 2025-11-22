package com.dreavee.healthylifetracker.ui.screens.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.data.database.entities.MealEntity
import com.dreavee.healthylifetracker.data.repository.TrackerRepository

import kotlinx.coroutines.launch


class FoodLoggingViewModel(
    private val repository: TrackerRepository,
    private val timeRuleEngine: com.dreavee.healthylifetracker.domain.logic.TimeRuleEngine
) : ViewModel() {

    fun logMeal(name: String, type: String, calories: Int) {
        viewModelScope.launch {
            val meal = MealEntity(
                timestamp = System.currentTimeMillis(),
                type = type,
                foodName = name,
                calories = calories,
                protein = 0f, // Placeholder
                carbs = 0f,
                fat = 0f
            )
            repository.logMeal(meal)
        }
    }
}
