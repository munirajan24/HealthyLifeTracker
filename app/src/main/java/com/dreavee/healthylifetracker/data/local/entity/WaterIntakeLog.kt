package com.dreavee.healthylifetracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

/**
 * Water intake log entry
 */
@Entity(tableName = "water_intake_log")
data class WaterIntakeLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String, // LocalDate as String (yyyy-MM-dd)
    val time: String, // LocalTime as String (HH:mm:ss)
    val amountMl: Int,
    val source: WaterSource = WaterSource.GLASS
)

enum class WaterSource {
    GLASS,      // ~200ml
    BOTTLE,     // ~500ml
    CUSTOM
}
