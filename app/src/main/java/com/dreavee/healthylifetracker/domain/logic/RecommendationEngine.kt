package com.dreavee.healthylifetracker.domain.logic

import com.dreavee.healthylifetracker.data.repository.TrackerRepository
import kotlinx.coroutines.flow.firstOrNull
import java.util.Calendar


class RecommendationEngine(
    private val repository: TrackerRepository
) {

    suspend fun getRecommendations(): List<Recommendation> {
        val recommendations = mutableListOf<Recommendation>()
        
        // Analyze today's data
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        val startOfDay = calendar.timeInMillis
        val endOfDay = System.currentTimeMillis()

        val waterLogs = repository.getWaterLogsForDay(startOfDay, endOfDay).firstOrNull() ?: emptyList()
        val totalWater = waterLogs.sumOf { it.amountMl }
        
        val meals = repository.getMealsForDay(startOfDay, endOfDay).firstOrNull() ?: emptyList()
        
        // 1. Hydration Analysis
        if (totalWater < 1000 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 12) {
            recommendations.add(
                Recommendation(
                    title = "Low Hydration",
                    message = "You've only drunk ${totalWater}ml today. Try to reach 2000ml!",
                    type = RecommendationType.WARNING
                )
            )
        } else if (totalWater > 2000) {
             recommendations.add(
                Recommendation(
                    title = "Great Hydration!",
                    message = "You hit your water goal today. Keep it up!",
                    type = RecommendationType.SUCCESS
                )
            )
        }

        // 2. Meal Timing Analysis
        if (meals.isEmpty() && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 10) {
             recommendations.add(
                Recommendation(
                    title = "Skipped Breakfast?",
                    message = "It's important to start your day with a healthy meal.",
                    type = RecommendationType.TIP
                )
            )
        }
        
        // 3. Workout Analysis
        val lastWorkout = repository.getLastWorkout()
        if (lastWorkout == null) {
             recommendations.add(
                Recommendation(
                    title = "Start Moving!",
                    message = "You haven't logged any workouts yet. Try a 10 min walk.",
                    type = RecommendationType.TIP
                )
            )
        } else {
            val daysSinceWorkout = (System.currentTimeMillis() - lastWorkout.startTime) / (24 * 60 * 60 * 1000)
            if (daysSinceWorkout > 2) {
                recommendations.add(
                    Recommendation(
                        title = "Missed the Gym?",
                        message = "It's been $daysSinceWorkout days since your last workout.",
                        type = RecommendationType.WARNING
                    )
                )
            }
        }

        // 4. General Tip
        recommendations.add(
             Recommendation(
                title = "Did you know?",
                message = "Drinking water 30 mins before a meal helps digestion.",
                type = RecommendationType.INFO
            )
        )

        return recommendations
    }
}

data class Recommendation(
    val title: String,
    val message: String,
    val type: RecommendationType
)

enum class RecommendationType {
    INFO, WARNING, SUCCESS, TIP
}
