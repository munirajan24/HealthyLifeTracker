package com.dreavee.healthylifetracker.core

object Constants {
    
    // Water defaults
    const val DEFAULT_WATER_GOAL_ML = 2100
    val DEFAULT_QUICK_ADD_OPTIONS = listOf(200, 250, 300)
    const val DEFAULT_WATER_REMINDER_INTERVAL = 60 // minutes
    const val DEFAULT_WATER_START_TIME = 480 // 8:00 AM
    const val DEFAULT_WATER_END_TIME = 1320 // 10:00 PM
    
    // Gym defaults
    const val DEFAULT_GYM_WEEKLY_TARGET = 4
    const val DEFAULT_GYM_REMINDER_TIME = 1140 // 7:00 PM
    
    // Meal defaults
    const val DEFAULT_BREAKFAST_TIME = 480 // 8:00 AM
    const val DEFAULT_LUNCH_TIME = 780 // 1:00 PM
    const val DEFAULT_SNACK_TIME = 960 // 4:00 PM
    const val DEFAULT_DINNER_TIME = 1200 // 8:00 PM
    const val DEFAULT_MEAL_PRE_REMINDER = 15 // minutes
    
    // Meal timing thresholds
    const val MEAL_ON_TIME_THRESHOLD_MINUTES = 30
    
    // Database
    const val DATABASE_NAME = "health_tracker_db"
    const val DATABASE_VERSION = 2
    
    // DataStore
    const val PREFERENCES_NAME = "user_settings"
}
