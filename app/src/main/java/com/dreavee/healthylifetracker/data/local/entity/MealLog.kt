package com.dreavee.healthylifetracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Meal log entry
 */
@Entity(tableName = "meal_log")
data class MealLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String, // LocalDate as String (yyyy-MM-dd)
    val mealType: MealType,
    val plannedTime: String, // LocalTime as String (HH:mm:ss)
    val actualTime: String?, // LocalTime as String (HH:mm:ss), null if not logged
    val status: MealStatus = MealStatus.PENDING,
    val notes: String? = null
)

enum class MealType {
    BREAKFAST, LUNCH, SNACK, DINNER
}

enum class MealStatus {
    PENDING,    // Not yet time or not logged
    ON_TIME,    // Logged within acceptable window
    LATE,       // Logged but late
    SKIPPED     // Marked as skipped
}
