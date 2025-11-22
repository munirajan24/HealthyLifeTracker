package com.dreavee.healthylifetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dreavee.healthylifetracker.data.database.entities.WaterLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterLogDao {
    @Insert
    suspend fun insertWaterLog(log: WaterLogEntity)

    @Query("SELECT * FROM water_logs WHERE timestamp >= :startTime AND timestamp <= :endTime ORDER BY timestamp DESC")
    fun getWaterLogsForDay(startTime: Long, endTime: Long): Flow<List<WaterLogEntity>>

    @Query("SELECT SUM(amountMl) FROM water_logs WHERE timestamp >= :startTime AND timestamp <= :endTime")
    fun getTotalWaterForDay(startTime: Long, endTime: Long): Flow<Int?>

    @Query("SELECT * FROM water_logs ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastWaterLog(): WaterLogEntity?
}
