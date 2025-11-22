package com.dreavee.healthylifetracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dreavee.healthylifetracker.data.local.dao.*
import com.dreavee.healthylifetracker.data.local.entity.*

@Database(
    entities = [
        UserSettings::class,
        WaterIntakeLog::class,
        WorkoutSession::class,
        MealLog::class
    ],
    version = 1,
    exportSchema = false
)
abstract class HealthTrackerDatabase : RoomDatabase() {
    
    abstract fun userSettingsDao(): UserSettingsDao
    abstract fun waterIntakeDao(): WaterIntakeDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun mealDao(): MealDao
    
    companion object {
        @Volatile
        private var INSTANCE: HealthTrackerDatabase? = null
        
        fun getDatabase(context: Context): HealthTrackerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealthTrackerDatabase::class.java,
                    "health_tracker_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
