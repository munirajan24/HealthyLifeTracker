package com.dreavee.healthylifetracker.data.local.dao

import androidx.room.*
import com.dreavee.healthylifetracker.data.local.entity.MealLog
import com.dreavee.healthylifetracker.data.local.entity.MealType
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: MealLog): Long
    
    @Update
    suspend fun update(log: MealLog)
    
    @Delete
    suspend fun delete(log: MealLog)
    
    @Query("SELECT * FROM meal_log WHERE date = :date ORDER BY mealType")
    fun getMealsForDate(date: String): Flow<List<MealLog>>
    
    @Query("SELECT * FROM meal_log WHERE date = :date AND mealType = :mealType LIMIT 1")
    suspend fun getMealForDate(date: String, mealType: MealType): MealLog?
    
    @Query("SELECT * FROM meal_log WHERE date >= :startDate AND date <= :endDate")
    fun getMealsForDateRange(startDate: String, endDate: String): Flow<List<MealLog>>
}
