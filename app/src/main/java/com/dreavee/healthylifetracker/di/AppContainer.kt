package com.dreavee.healthylifetracker.di

import android.content.Context
import androidx.room.Room
import com.dreavee.healthylifetracker.data.database.AppDatabase
import com.dreavee.healthylifetracker.data.repository.TrackerRepository
import com.dreavee.healthylifetracker.domain.logic.RecommendationEngine
import com.dreavee.healthylifetracker.domain.logic.StreakManager
import com.dreavee.healthylifetracker.domain.logic.TimeRuleEngine
import com.dreavee.healthylifetracker.domain.logic.TimelinePredictor
import com.dreavee.healthylifetracker.util.NotificationHelper

class AppContainer(private val context: Context) {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "healthy_life_tracker_db"
        ).build()
    }

    val trackerRepository: TrackerRepository by lazy {
        TrackerRepository(
            mealDao = database.mealDao(),
            waterLogDao = database.waterLogDao(),
            workoutLogDao = database.workoutLogDao()
        )
    }

    val timeRuleEngine: TimeRuleEngine by lazy {
        TimeRuleEngine()
    }

    val timelinePredictor: TimelinePredictor by lazy {
        TimelinePredictor(
            repository = trackerRepository,
            timeRuleEngine = timeRuleEngine
        )
    }

    val streakManager: StreakManager by lazy {
        StreakManager(
            repository = trackerRepository
        )
    }

    val recommendationEngine: RecommendationEngine by lazy {
        RecommendationEngine(
            repository = trackerRepository
        )
    }

    val notificationHelper: NotificationHelper by lazy {
        NotificationHelper(context)
    }
}
