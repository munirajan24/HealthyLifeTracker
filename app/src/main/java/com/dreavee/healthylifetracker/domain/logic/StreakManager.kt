package com.dreavee.healthylifetracker.domain.logic

import com.dreavee.healthylifetracker.data.repository.TrackerRepository
import kotlinx.coroutines.flow.firstOrNull
import java.util.Calendar


class StreakManager(
    private val repository: TrackerRepository
) {

    suspend fun calculateStreaks(): StreakData {
        // This is a simplified streak calculation.
        // In a real app, we would query the DB for daily completion.
        // For now, we'll check if there's any activity today and yesterday.
        
        val todayStart = getStartOfDay(0)
        val todayEnd = getEndOfDay(0)

        val todayMeals = repository.getMealsForDay(todayStart, todayEnd).firstOrNull()
        val todayWater = repository.getTotalWaterForDay(todayStart, todayEnd).firstOrNull() ?: 0
        val todayWorkout = repository.getWorkoutsForDay(todayStart, todayEnd).firstOrNull()

        val hasActivityToday = (todayMeals?.isNotEmpty() == true) || (todayWater > 0) || (todayWorkout?.isNotEmpty() == true)
        
        // Mocking streak for now as we don't have historical data generation
        val currentStreak = if (hasActivityToday) 1 else 0
        
        return StreakData(
            currentStreak = currentStreak,
            longestStreak = currentStreak, // Placeholder
            lastActivityDate = if (hasActivityToday) System.currentTimeMillis() else 0L
        )
    }

    private fun getStartOfDay(dayOffset: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, dayOffset)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun getEndOfDay(dayOffset: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, dayOffset)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.timeInMillis
    }
}

data class StreakData(
    val currentStreak: Int,
    val longestStreak: Int,
    val lastActivityDate: Long
)
