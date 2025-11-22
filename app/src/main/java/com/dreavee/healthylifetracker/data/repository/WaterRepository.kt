package com.dreavee.healthylifetracker.data.repository

import com.dreavee.healthylifetracker.data.local.dao.WaterIntakeDao
import com.dreavee.healthylifetracker.data.local.entity.WaterIntakeLog
import com.dreavee.healthylifetracker.data.local.entity.WaterSource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WaterRepository(private val waterIntakeDao: WaterIntakeDao) {
    
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    
    fun getLogsForToday(): Flow<List<WaterIntakeLog>> {
        val today = LocalDate.now().format(dateFormatter)
        return waterIntakeDao.getLogsForDate(today)
    }
    
    fun getTotalForToday(): Flow<Int?> {
        val today = LocalDate.now().format(dateFormatter)
        return waterIntakeDao.getTotalForDate(today)
    }
    
    suspend fun addWaterIntake(amountMl: Int, source: WaterSource = WaterSource.CUSTOM) {
        val now = java.time.LocalTime.now()
        val log = WaterIntakeLog(
            date = LocalDate.now().format(dateFormatter),
            time = now.format(DateTimeFormatter.ISO_LOCAL_TIME),
            amountMl = amountMl,
            source = source
        )
        waterIntakeDao.insert(log)
    }
    
    suspend fun deleteLog(log: WaterIntakeLog) {
        waterIntakeDao.delete(log)
    }
    
    suspend fun getWeeklyAverage(): Double {
        val endDate = LocalDate.now()
        val startDate = endDate.minusDays(6)
        val totals = waterIntakeDao.getDailyTotals(
            startDate.format(dateFormatter),
            endDate.format(dateFormatter)
        )
        return if (totals.isNotEmpty()) {
            totals.map { it.total }.average()
        } else 0.0
    }
}
