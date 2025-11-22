package com.dreavee.healthylifetracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Workout/Gym session
 */
@Entity(tableName = "workout_session")
data class WorkoutSession(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String, // LocalDate as String (yyyy-MM-dd)
    val startTime: String, // LocalTime as String (HH:mm:ss)
    val endTime: String?, // LocalTime as String (HH:mm:ss)
    val type: String, // e.g., "Push day", "Cardio", "Legs"
    val intensity: Intensity = Intensity.MEDIUM,
    val notes: String? = null,
    val completed: Boolean = false
)

enum class Intensity {
    LOW, MEDIUM, HIGH
}
