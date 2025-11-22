package com.dreavee.healthylifetracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * User settings and preferences
 */
@Entity(tableName = "user_settings")
data class UserSettings(
    @PrimaryKey val id: Int = 1,
    val gender: Gender = Gender.OTHER,
    val weightKg: Float? = null,
    val prefersDarkTheme: Boolean = false,
    val languageCode: String = "en",
    
    // Water settings
    val waterGoalMl: Int = 2100,
    val waterReminderEnabled: Boolean = true,
    val waterReminderFromHour: Int = 8,
    val waterReminderFromMinute: Int = 0,
    val waterReminderToHour: Int = 22,
    val waterReminderToMinute: Int = 0,
    val waterReminderIntervalMinutes: Int = 60,
    val furtherReminderEnabled: Boolean = false,
    
    // Gym settings
    val gymWeeklyTargetSessions: Int = 4,
    val gymReminderEnabled: Boolean = true,
    val gymReminderHour: Int = 18,
    val gymReminderMinute: Int = 0,
    
    // Meal settings
    val mealReminderEnabled: Boolean = true,
    val breakfastHour: Int = 8,
    val breakfastMinute: Int = 0,
    val lunchHour: Int = 13,
    val lunchMinute: Int = 0,
    val snackHour: Int = 16,
    val snackMinute: Int = 0,
    val dinnerHour: Int = 20,
    val dinnerMinute: Int = 0
)

enum class Gender {
    MALE, FEMALE, OTHER
}
