package com.dreavee.healthylifetracker.core.util

import kotlin.math.min

object PercentageCalculator {
    
    /**
     * Calculate water completion percentage
     * @param totalMl Current water intake in ml
     * @param goalMl Daily goal in ml
     * @return Percentage (0-100)
     */
    fun calculateWaterCompletion(totalMl: Int, goalMl: Int): Float {
        if (goalMl <= 0) return 0f
        return min(100f, (totalMl.toFloat() / goalMl) * 100f)
    }
    
    /**
     * Calculate gym completion percentage
     * @param completedSessions Number of completed sessions
     * @param targetSessions Weekly target
     * @return Percentage (0-100)
     */
    fun calculateGymCompletion(completedSessions: Int, targetSessions: Int): Float {
        if (targetSessions <= 0) return 0f
        return min(100f, (completedSessions.toFloat() / targetSessions) * 100f)
    }
    
    /**
     * Calculate meal timing score
     * @param onTimeCount Number of on-time meals
     * @param lateCount Number of late meals
     * @param skippedCount Number of skipped meals
     * @param totalEnabled Total enabled meals
     * @return Score (0-100)
     */
    fun calculateMealScore(
        onTimeCount: Int,
        lateCount: Int,
        skippedCount: Int,
        totalEnabled: Int
    ): Float {
        if (totalEnabled <= 0) return 0f
        
        val score = (onTimeCount * 1.0) + (lateCount * 0.5) + (skippedCount * 0.0)
        return ((score / totalEnabled) * 100).toFloat()
    }
}
