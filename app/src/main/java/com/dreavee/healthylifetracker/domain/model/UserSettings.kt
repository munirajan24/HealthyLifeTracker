package com.dreavee.healthylifetracker.domain.model

import com.dreavee.healthylifetracker.core.Constants
import java.time.DayOfWeek

/**
 * Complete user settings and preferences
 * Single source of truth for all app configuration
 */
data class UserSettings(
    // Profile
    val name: String = "",
    val gender: Gender = Gender.UNKNOWN,
    val weightKg: Float? = null,
    
    // Water settings
    val waterDailyGoalMl: Int = Constants.DEFAULT_WATER_GOAL_ML,
    val waterQuickAddOptions: List<Int> = Constants.DEFAULT_QUICK_ADD_OPTIONS,
    val waterReminderEnabled: Boolean = true,
    val waterReminderStartMinutes: Int = Constants.DEFAULT_WATER_START_TIME,
    val waterReminderEndMinutes: Int = Constants.DEFAULT_WATER_END_TIME,
    val waterReminderIntervalMinutes: Int = Constants.DEFAULT_WATER_REMINDER_INTERVAL,
    val waterFurtherReminderEnabled: Boolean = false,
    
    // Gym settings
    val gymTrackingEnabled: Boolean = true,
    val gymWeeklyTargetSessions: Int = Constants.DEFAULT_GYM_WEEKLY_TARGET,
    val gymReminderEnabled: Boolean = true,
    val gymReminderTimeMinutes: Int = Constants.DEFAULT_GYM_REMINDER_TIME,
    val gymReminderDaysOfWeek: Set<DayOfWeek> = setOf(
        DayOfWeek.MONDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.FRIDAY
    ),
    
    // Meals settings
    val mealsTrackingEnabled: Boolean = true,
    
    // Breakfast
    val breakfastEnabled: Boolean = true,
    val breakfastPlannedMinutes: Int = Constants.DEFAULT_BREAKFAST_TIME,
    val breakfastPreReminderMinutes: Int = Constants.DEFAULT_MEAL_PRE_REMINDER,
    
    // Lunch
    val lunchEnabled: Boolean = true,
    val lunchPlannedMinutes: Int = Constants.DEFAULT_LUNCH_TIME,
    val lunchPreReminderMinutes: Int = Constants.DEFAULT_MEAL_PRE_REMINDER,
    
    // Snack
    val snackEnabled: Boolean = true,
    val snackPlannedMinutes: Int = Constants.DEFAULT_SNACK_TIME,
    val snackPreReminderMinutes: Int = Constants.DEFAULT_MEAL_PRE_REMINDER,
    
    // Dinner
    val dinnerEnabled: Boolean = true,
    val dinnerPlannedMinutes: Int = Constants.DEFAULT_DINNER_TIME,
    val dinnerPreReminderMinutes: Int = Constants.DEFAULT_MEAL_PRE_REMINDER,
    
    // App settings
    val theme: AppTheme = AppTheme.SYSTEM,
    val use24hFormat: Boolean = true,
    val waterUnit: WaterUnit = WaterUnit.MILLILITER
)

enum class Gender {
    UNKNOWN, MALE, FEMALE, OTHER
}

enum class AppTheme {
    SYSTEM, LIGHT, DARK
}

enum class WaterUnit {
    MILLILITER, LITER
}
