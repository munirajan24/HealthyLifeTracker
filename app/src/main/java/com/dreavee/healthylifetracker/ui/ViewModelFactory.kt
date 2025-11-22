package com.dreavee.healthylifetracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dreavee.healthylifetracker.di.AppContainer
import com.dreavee.healthylifetracker.ui.screens.ai.AIInsightsViewModel
import com.dreavee.healthylifetracker.ui.screens.analytics.AnalyticsViewModel
import com.dreavee.healthylifetracker.ui.screens.calendar.CalendarViewModel
import com.dreavee.healthylifetracker.ui.screens.dashboard.DashboardViewModel
import com.dreavee.healthylifetracker.ui.screens.food.FoodLoggingViewModel
import com.dreavee.healthylifetracker.ui.screens.gym.GymLoggingViewModel
import com.dreavee.healthylifetracker.ui.screens.water.WaterLoggingViewModel

class ViewModelFactory(private val appContainer: AppContainer) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(
                    trackerRepository = appContainer.trackerRepository,
                    timelinePredictor = appContainer.timelinePredictor,
                    streakManager = appContainer.streakManager,
                    recommendationEngine = appContainer.recommendationEngine
                ) as T
            }
            modelClass.isAssignableFrom(WaterLoggingViewModel::class.java) -> {
                WaterLoggingViewModel(
                    repository = appContainer.trackerRepository,
                    notificationHelper = appContainer.notificationHelper,
                    timeRuleEngine = appContainer.timeRuleEngine
                ) as T
            }
            modelClass.isAssignableFrom(FoodLoggingViewModel::class.java) -> {
                FoodLoggingViewModel(
                    repository = appContainer.trackerRepository,
                    timeRuleEngine = appContainer.timeRuleEngine
                ) as T
            }
            modelClass.isAssignableFrom(GymLoggingViewModel::class.java) -> {
                GymLoggingViewModel(
                    repository = appContainer.trackerRepository
                ) as T
            }
            modelClass.isAssignableFrom(CalendarViewModel::class.java) -> {
                CalendarViewModel(
                    streakManager = appContainer.streakManager
                ) as T
            }
            modelClass.isAssignableFrom(AnalyticsViewModel::class.java) -> {
                AnalyticsViewModel(
                    repository = appContainer.trackerRepository
                ) as T
            }
            modelClass.isAssignableFrom(AIInsightsViewModel::class.java) -> {
                AIInsightsViewModel(
                    recommendationEngine = appContainer.recommendationEngine
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
