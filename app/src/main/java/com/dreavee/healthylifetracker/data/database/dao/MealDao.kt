package com.dreavee.healthylifetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dreavee.healthylifetracker.data.database.entities.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert
    suspend fun insertMeal(meal: MealEntity)

    @Query("SELECT * FROM meals ORDER BY timestamp DESC")
    fun getAllMeals(): Flow<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE timestamp >= :startTime AND timestamp <= :endTime ORDER BY timestamp DESC")
    fun getMealsForDay(startTime: Long, endTime: Long): Flow<List<MealEntity>>

    @Query("SELECT * FROM meals ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastMeal(): MealEntity?
}
