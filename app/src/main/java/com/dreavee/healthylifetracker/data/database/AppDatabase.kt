package com.dreavee.healthylifetracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dreavee.healthylifetracker.data.database.dao.MealDao
import com.dreavee.healthylifetracker.data.database.dao.WaterLogDao
import com.dreavee.healthylifetracker.data.database.dao.WorkoutLogDao
import com.dreavee.healthylifetracker.data.database.entities.MealEntity
import com.dreavee.healthylifetracker.data.database.entities.WaterLogEntity
import com.dreavee.healthylifetracker.data.database.entities.WorkoutLogEntity

@Database(
    entities = [MealEntity::class, WaterLogEntity::class, WorkoutLogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun waterLogDao(): WaterLogDao
    abstract fun workoutLogDao(): WorkoutLogDao
}
