package com.dreavee.healthylifetracker.data.local.dao

import androidx.room.*
import com.dreavee.healthylifetracker.data.local.entity.WaterIntakeLog
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterIntakeDao {
    
    @Insert
    suspend fun insert(log: WaterIntakeLog): Long
    
    @Delete
    suspend fun delete(log: WaterIntakeLog)
    
    @Query("SELECT * FROM water_intake_log WHERE date = :date ORDER BY time DESC")
    fun getLogsForDate(date: String): Flow<List<WaterIntakeLog>>
    
    @Query("SELECT SUM(amountMl) FROM water_intake_log WHERE date = :date")
    fun getTotalForDate(date: String): Flow<Int?>
    
    @Query("SELECT SUM(amountMl) FROM water_intake_log WHERE date = :date")
    suspend fun getTotalForDateOnce(date: String): Int?
    
    @Query("SELECT * FROM water_intake_log WHERE date >= :startDate AND date <= :endDate ORDER BY date, time")
    fun getLogsForDateRange(startDate: String, endDate: String): Flow<List<WaterIntakeLog>>
    
    @Query("SELECT date, SUM(amountMl) as total FROM water_intake_log WHERE date >= :startDate AND date <= :endDate GROUP BY date")
    suspend fun getDailyTotals(startDate: String, endDate: String): List<DailyTotal>
}

data class DailyTotal(
    val date: String,
    val total: Int
)
