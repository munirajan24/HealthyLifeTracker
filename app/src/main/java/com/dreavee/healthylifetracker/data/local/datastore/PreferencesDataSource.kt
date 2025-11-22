package com.dreavee.healthylifetracker.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.dreavee.healthylifetracker.core.Constants
import com.dreavee.healthylifetracker.domain.model.AppTheme
import com.dreavee.healthylifetracker.domain.model.Gender
import com.dreavee.healthylifetracker.domain.model.UserSettings
import com.dreavee.healthylifetracker.domain.model.WaterUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREFERENCES_NAME)

class PreferencesDataSource(private val context: Context) {
    
    private object Keys {
        // Profile
        val NAME = stringPreferencesKey("name")
        val GENDER = stringPreferencesKey("gender")
        val WEIGHT_KG = floatPreferencesKey("weight_kg")
        
        // Water
        val WATER_GOAL_ML = intPreferencesKey("water_goal_ml")
        val WATER_QUICK_ADD_1 = intPreferencesKey("water_quick_add_1")
        val WATER_QUICK_ADD_2 = intPreferencesKey("water_quick_add_2")
        val WATER_QUICK_ADD_3 = intPreferencesKey("water_quick_add_3")
        val WATER_REMINDER_ENABLED = booleanPreferencesKey("water_reminder_enabled")
        val WATER_REMINDER_START = intPreferencesKey("water_reminder_start")
        val WATER_REMINDER_END = intPreferencesKey("water_reminder_end")
        val WATER_REMINDER_INTERVAL = intPreferencesKey("water_reminder_interval")
        val WATER_FURTHER_REMINDER = booleanPreferencesKey("water_further_reminder")
        
        // Gym
        val GYM_TRACKING_ENABLED = booleanPreferencesKey("gym_tracking_enabled")
        val GYM_WEEKLY_TARGET = intPreferencesKey("gym_weekly_target")
        val GYM_REMINDER_ENABLED = booleanPreferencesKey("gym_reminder_enabled")
        val GYM_REMINDER_TIME = intPreferencesKey("gym_reminder_time")
        val GYM_REMINDER_DAYS = stringPreferencesKey("gym_reminder_days")
        
        // Meals
        val MEALS_TRACKING_ENABLED = booleanPreferencesKey("meals_tracking_enabled")
        val BREAKFAST_ENABLED = booleanPreferencesKey("breakfast_enabled")
        val BREAKFAST_TIME = intPreferencesKey("breakfast_time")
        val BREAKFAST_PRE_REMINDER = intPreferencesKey("breakfast_pre_reminder")
        val LUNCH_ENABLED = booleanPreferencesKey("lunch_enabled")
        val LUNCH_TIME = intPreferencesKey("lunch_time")
        val LUNCH_PRE_REMINDER = intPreferencesKey("lunch_pre_reminder")
        val SNACK_ENABLED = booleanPreferencesKey("snack_enabled")
        val SNACK_TIME = intPreferencesKey("snack_time")
        val SNACK_PRE_REMINDER = intPreferencesKey("snack_pre_reminder")
        val DINNER_ENABLED = booleanPreferencesKey("dinner_enabled")
        val DINNER_TIME = intPreferencesKey("dinner_time")
        val DINNER_PRE_REMINDER = intPreferencesKey("dinner_pre_reminder")
        
        // App
        val THEME = stringPreferencesKey("theme")
        val USE_24H_FORMAT = booleanPreferencesKey("use_24h_format")
        val WATER_UNIT = stringPreferencesKey("water_unit")
    }
    
    val userSettings: Flow<UserSettings> = context.dataStore.data.map { prefs ->
        UserSettings(
            // Profile
            name = prefs[Keys.NAME] ?: "",
            gender = Gender.valueOf(prefs[Keys.GENDER] ?: Gender.UNKNOWN.name),
            weightKg = prefs[Keys.WEIGHT_KG],
            
            // Water
            waterDailyGoalMl = prefs[Keys.WATER_GOAL_ML] ?: Constants.DEFAULT_WATER_GOAL_ML,
            waterQuickAddOptions = listOf(
                prefs[Keys.WATER_QUICK_ADD_1] ?: 200,
                prefs[Keys.WATER_QUICK_ADD_2] ?: 250,
                prefs[Keys.WATER_QUICK_ADD_3] ?: 300
            ),
            waterReminderEnabled = prefs[Keys.WATER_REMINDER_ENABLED] ?: true,
            waterReminderStartMinutes = prefs[Keys.WATER_REMINDER_START] ?: Constants.DEFAULT_WATER_START_TIME,
            waterReminderEndMinutes = prefs[Keys.WATER_REMINDER_END] ?: Constants.DEFAULT_WATER_END_TIME,
            waterReminderIntervalMinutes = prefs[Keys.WATER_REMINDER_INTERVAL] ?: Constants.DEFAULT_WATER_REMINDER_INTERVAL,
            waterFurtherReminderEnabled = prefs[Keys.WATER_FURTHER_REMINDER] ?: false,
            
            // Gym
            gymTrackingEnabled = prefs[Keys.GYM_TRACKING_ENABLED] ?: true,
            gymWeeklyTargetSessions = prefs[Keys.GYM_WEEKLY_TARGET] ?: Constants.DEFAULT_GYM_WEEKLY_TARGET,
            gymReminderEnabled = prefs[Keys.GYM_REMINDER_ENABLED] ?: true,
            gymReminderTimeMinutes = prefs[Keys.GYM_REMINDER_TIME] ?: Constants.DEFAULT_GYM_REMINDER_TIME,
            gymReminderDaysOfWeek = parseDaysOfWeek(prefs[Keys.GYM_REMINDER_DAYS]),
            
            // Meals
            mealsTrackingEnabled = prefs[Keys.MEALS_TRACKING_ENABLED] ?: true,
            breakfastEnabled = prefs[Keys.BREAKFAST_ENABLED] ?: true,
            breakfastPlannedMinutes = prefs[Keys.BREAKFAST_TIME] ?: Constants.DEFAULT_BREAKFAST_TIME,
            breakfastPreReminderMinutes = prefs[Keys.BREAKFAST_PRE_REMINDER] ?: Constants.DEFAULT_MEAL_PRE_REMINDER,
            lunchEnabled = prefs[Keys.LUNCH_ENABLED] ?: true,
            lunchPlannedMinutes = prefs[Keys.LUNCH_TIME] ?: Constants.DEFAULT_LUNCH_TIME,
            lunchPreReminderMinutes = prefs[Keys.LUNCH_PRE_REMINDER] ?: Constants.DEFAULT_MEAL_PRE_REMINDER,
            snackEnabled = prefs[Keys.SNACK_ENABLED] ?: true,
            snackPlannedMinutes = prefs[Keys.SNACK_TIME] ?: Constants.DEFAULT_SNACK_TIME,
            snackPreReminderMinutes = prefs[Keys.SNACK_PRE_REMINDER] ?: Constants.DEFAULT_MEAL_PRE_REMINDER,
            dinnerEnabled = prefs[Keys.DINNER_ENABLED] ?: true,
            dinnerPlannedMinutes = prefs[Keys.DINNER_TIME] ?: Constants.DEFAULT_DINNER_TIME,
            dinnerPreReminderMinutes = prefs[Keys.DINNER_PRE_REMINDER] ?: Constants.DEFAULT_MEAL_PRE_REMINDER,
            
            // App
            theme = AppTheme.valueOf(prefs[Keys.THEME] ?: AppTheme.SYSTEM.name),
            use24hFormat = prefs[Keys.USE_24H_FORMAT] ?: true,
            waterUnit = WaterUnit.valueOf(prefs[Keys.WATER_UNIT] ?: WaterUnit.MILLILITER.name)
        )
    }
    
    suspend fun updateSettings(update: (UserSettings) -> UserSettings) {
        context.dataStore.edit { prefs ->
            // Get current settings
            val current = userSettings.map { it }.first()
            val updated = update(current)
            
            // Save all fields
            prefs[Keys.NAME] = updated.name
            prefs[Keys.GENDER] = updated.gender.name
            updated.weightKg?.let { prefs[Keys.WEIGHT_KG] = it }
            
            prefs[Keys.WATER_GOAL_ML] = updated.waterDailyGoalMl
            prefs[Keys.WATER_QUICK_ADD_1] = updated.waterQuickAddOptions.getOrElse(0) { 200 }
            prefs[Keys.WATER_QUICK_ADD_2] = updated.waterQuickAddOptions.getOrElse(1) { 250 }
            prefs[Keys.WATER_QUICK_ADD_3] = updated.waterQuickAddOptions.getOrElse(2) { 300 }
            prefs[Keys.WATER_REMINDER_ENABLED] = updated.waterReminderEnabled
            prefs[Keys.WATER_REMINDER_START] = updated.waterReminderStartMinutes
            prefs[Keys.WATER_REMINDER_END] = updated.waterReminderEndMinutes
            prefs[Keys.WATER_REMINDER_INTERVAL] = updated.waterReminderIntervalMinutes
            prefs[Keys.WATER_FURTHER_REMINDER] = updated.waterFurtherReminderEnabled
            
            prefs[Keys.GYM_TRACKING_ENABLED] = updated.gymTrackingEnabled
            prefs[Keys.GYM_WEEKLY_TARGET] = updated.gymWeeklyTargetSessions
            prefs[Keys.GYM_REMINDER_ENABLED] = updated.gymReminderEnabled
            prefs[Keys.GYM_REMINDER_TIME] = updated.gymReminderTimeMinutes
            prefs[Keys.GYM_REMINDER_DAYS] = serializeDaysOfWeek(updated.gymReminderDaysOfWeek)
            
            prefs[Keys.MEALS_TRACKING_ENABLED] = updated.mealsTrackingEnabled
            prefs[Keys.BREAKFAST_ENABLED] = updated.breakfastEnabled
            prefs[Keys.BREAKFAST_TIME] = updated.breakfastPlannedMinutes
            prefs[Keys.BREAKFAST_PRE_REMINDER] = updated.breakfastPreReminderMinutes
            prefs[Keys.LUNCH_ENABLED] = updated.lunchEnabled
            prefs[Keys.LUNCH_TIME] = updated.lunchPlannedMinutes
            prefs[Keys.LUNCH_PRE_REMINDER] = updated.lunchPreReminderMinutes
            prefs[Keys.SNACK_ENABLED] = updated.snackEnabled
            prefs[Keys.SNACK_TIME] = updated.snackPlannedMinutes
            prefs[Keys.SNACK_PRE_REMINDER] = updated.snackPreReminderMinutes
            prefs[Keys.DINNER_ENABLED] = updated.dinnerEnabled
            prefs[Keys.DINNER_TIME] = updated.dinnerPlannedMinutes
            prefs[Keys.DINNER_PRE_REMINDER] = updated.dinnerPreReminderMinutes
            
            prefs[Keys.THEME] = updated.theme.name
            prefs[Keys.USE_24H_FORMAT] = updated.use24hFormat
            prefs[Keys.WATER_UNIT] = updated.waterUnit.name
        }
    }
    
    private fun parseDaysOfWeek(value: String?): Set<DayOfWeek> {
        if (value.isNullOrEmpty()) return setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)
        return value.split(",").mapNotNull {
            try { DayOfWeek.valueOf(it) } catch (e: Exception) { null }
        }.toSet()
    }
    
    private fun serializeDaysOfWeek(days: Set<DayOfWeek>): String {
        return days.joinToString(",") { it.name }
    }
}
