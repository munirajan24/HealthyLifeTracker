package com.dreavee.healthylifetracker.data.local.dao

import androidx.room.*
import com.dreavee.healthylifetracker.data.local.entity.WorkoutSession
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    
    @Insert
    suspend fun insert(session: WorkoutSession): Long
    
    @Update
    suspend fun update(session: WorkoutSession)
    
    @Delete
    suspend fun delete(session: WorkoutSession)
    
    @Query("SELECT * FROM workout_session WHERE date = :date")
    fun getSessionsForDate(date: String): Flow<List<WorkoutSession>>
    
    @Query("SELECT * FROM workout_session WHERE date = :date AND completed = 1 LIMIT 1")
    suspend fun getCompletedSessionForDate(date: String): WorkoutSession?
    
    @Query("SELECT * FROM workout_session WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    fun getSessionsForDateRange(startDate: String, endDate: String): Flow<List<WorkoutSession>>
    
    @Query("SELECT COUNT(*) FROM workout_session WHERE date >= :startDate AND date <= :endDate AND completed = 1")
    suspend fun getCompletedCount(startDate: String, endDate: String): Int
}
