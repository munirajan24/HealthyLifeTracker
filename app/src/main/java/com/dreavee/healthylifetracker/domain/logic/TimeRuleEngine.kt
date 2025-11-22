package com.dreavee.healthylifetracker.domain.logic

import com.dreavee.healthylifetracker.data.database.entities.MealEntity


class TimeRuleEngine {

    companion object {
        const val WATER_WAIT_AFTER_MEAL_MS = 30 * 60 * 1000L // 30 mins
        const val WATER_STOP_BEFORE_MEAL_MS = 20 * 60 * 1000L // 20 mins
        const val MEAL_GAP_MS = 3 * 60 * 60 * 1000L // 3 hours (Example gap)
    }

    fun canDrinkWater(currentTime: Long, lastMeal: MealEntity?): Boolean {
        if (lastMeal == null) return true
        val timeSinceMeal = currentTime - lastMeal.timestamp
        return timeSinceMeal >= WATER_WAIT_AFTER_MEAL_MS
    }

    fun getNextSafeWaterTime(lastMeal: MealEntity?): Long {
        if (lastMeal == null) return 0L
        return lastMeal.timestamp + WATER_WAIT_AFTER_MEAL_MS
    }

    fun getNextRecommendedMealTime(lastMeal: MealEntity?): Long {
        if (lastMeal == null) return 0L // Should be handled by UI to suggest "Now"
        return lastMeal.timestamp + MEAL_GAP_MS
    }

    fun getWaterStopWarningTime(nextMealTime: Long): Long {
        return nextMealTime - WATER_STOP_BEFORE_MEAL_MS
    }
}
