package com.dreavee.healthylifetracker.core.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    
    /**
     * Get current date as string key (yyyy-MM-dd)
     */
    fun getCurrentDateKey(): String {
        return LocalDate.now().format(dateFormatter)
    }
    
    /**
     * Convert LocalTime to minutes from midnight
     */
    fun getMinutesFromMidnight(time: LocalTime): Int {
        return time.hour * 60 + time.minute
    }
    
    /**
     * Convert minutes from midnight to LocalTime
     */
    fun getLocalTimeFromMinutes(minutes: Int): LocalTime {
        val hours = minutes / 60
        val mins = minutes % 60
        return LocalTime.of(hours, mins)
    }
    
    /**
     * Get start and end date for the current week (Mon-Sun)
     */
    fun getWeekRange(date: LocalDate = LocalDate.now()): Pair<LocalDate, LocalDate> {
        val monday = date.with(DayOfWeek.MONDAY)
        val sunday = monday.plusDays(6)
        return monday to sunday
    }
    
    /**
     * Get start and end date for the current month
     */
    fun getMonthRange(date: LocalDate = LocalDate.now()): Pair<LocalDate, LocalDate> {
        val firstDay = date.withDayOfMonth(1)
        val lastDay = date.withDayOfMonth(date.lengthOfMonth())
        return firstDay to lastDay
    }
    
    /**
     * Format date key to readable string
     */
    fun formatDateKey(dateKey: String): String {
        return try {
            val date = LocalDate.parse(dateKey, dateFormatter)
            date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"))
        } catch (e: Exception) {
            dateKey
        }
    }
    
    /**
     * Get greeting based on time of day
     */
    fun getGreeting(): String {
        val hour = LocalTime.now().hour
        return when (hour) {
            in 0..11 -> "morning"
            in 12..16 -> "afternoon"
            else -> "evening"
        }
    }
}
