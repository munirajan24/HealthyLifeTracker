package com.dreavee.healthylifetracker.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long,
    val type: String, // Breakfast, Lunch, Dinner, Snack, Pre-workout, Post-workout
    val foodName: String,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float,
    val qualityScore: Int = 0 // AI Score
)
