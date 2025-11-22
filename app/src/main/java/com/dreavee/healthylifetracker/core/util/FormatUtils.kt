package com.dreavee.healthylifetracker.core.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object FormatUtils {
    
    /**
     * Format water amount based on unit preference
     */
    fun formatWaterAmount(ml: Int, unit: WaterUnit): String {
        return when (unit) {
            WaterUnit.MILLILITER -> "${ml}ml"
            WaterUnit.LITER -> String.format("%.2fL", ml / 1000f)
        }
    }
    
    /**
     * Format time based on 12h/24h preference
     */
    fun formatTime(minutes: Int, use24h: Boolean): String {
        val time = DateTimeUtils.getLocalTimeFromMinutes(minutes)
        return if (use24h) {
            time.format(DateTimeFormatter.ofPattern("HH:mm"))
        } else {
            time.format(DateTimeFormatter.ofPattern("h:mm a"))
        }
    }
    
    /**
     * Format LocalTime based on preference
     */
    fun formatTime(time: LocalTime, use24h: Boolean): String {
        return if (use24h) {
            time.format(DateTimeFormatter.ofPattern("HH:mm"))
        } else {
            time.format(DateTimeFormatter.ofPattern("h:mm a"))
        }
    }
    
    /**
     * Format percentage with one decimal place
     */
    fun formatPercentage(percentage: Float): String {
        return String.format("%.1f%%", percentage)
    }
}

enum class WaterUnit {
    MILLILITER,
    LITER
}
