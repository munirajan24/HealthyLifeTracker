package com.dreavee.healthylifetracker.domain.logic

import com.dreavee.healthylifetracker.data.repository.TrackerRepository


data class TimelineEvent(
    val time: Long,
    val type: EventType,
    val message: String,
    val isActionable: Boolean = false
)

enum class EventType {
    WATER, FOOD, WORKOUT
}

class TimelinePredictor(
    private val repository: TrackerRepository,
    private val timeRuleEngine: TimeRuleEngine
) {

    suspend fun getTimelineEvents(currentTime: Long): List<TimelineEvent> {
        val events = mutableListOf<TimelineEvent>()
        val lastMeal = repository.getLastMeal()
        val lastWater = repository.getLastWaterLog()
        val lastWorkout = repository.getLastWorkout()

        // 1. Water Logic
        if (timeRuleEngine.canDrinkWater(currentTime, lastMeal)) {
            events.add(
                TimelineEvent(
                    time = currentTime,
                    type = EventType.WATER,
                    message = "Safe to drink water now",
                    isActionable = true
                )
            )
        } else {
            val safeTime = timeRuleEngine.getNextSafeWaterTime(lastMeal)
            events.add(
                TimelineEvent(
                    time = safeTime,
                    type = EventType.WATER,
                    message = "Next safe water time",
                    isActionable = false
                )
            )
        }

        // 2. Food Logic
        val nextMealTime = if (lastMeal != null) {
            timeRuleEngine.getNextRecommendedMealTime(lastMeal)
        } else {
            currentTime // Eat now if no logs
        }
        
        if (nextMealTime <= currentTime) {
             events.add(
                TimelineEvent(
                    time = currentTime,
                    type = EventType.FOOD,
                    message = "Time for a meal!",
                    isActionable = true
                )
            )
        } else {
             events.add(
                TimelineEvent(
                    time = nextMealTime,
                    type = EventType.FOOD,
                    message = "Next recommended meal",
                    isActionable = false
                )
            )
        }

        // 3. Workout Logic (Simple rule: 1 hour after meal)
        if (lastMeal != null) {
             val workoutTime = lastMeal.timestamp + (60 * 60 * 1000L)
             if (workoutTime > currentTime) {
                 events.add(
                    TimelineEvent(
                        time = workoutTime,
                        type = EventType.WORKOUT,
                        message = "Gym recommended",
                        isActionable = false
                    )
                )
             } else {
                 events.add(
                    TimelineEvent(
                        time = currentTime,
                        type = EventType.WORKOUT,
                        message = "Good time to workout",
                        isActionable = true
                    )
                )
             }
        }

        return events.sortedBy { it.time }
    }
}
