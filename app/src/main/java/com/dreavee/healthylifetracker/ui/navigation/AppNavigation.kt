package com.dreavee.healthylifetracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dreavee.healthylifetracker.ui.screens.dashboard.DashboardScreen
import com.dreavee.healthylifetracker.ui.screens.ai.AIInsightsScreen
import com.dreavee.healthylifetracker.ui.screens.analytics.AnalyticsScreen
import com.dreavee.healthylifetracker.ui.screens.calendar.CalendarScreen
import com.dreavee.healthylifetracker.ui.screens.food.FoodLoggingScreen
import com.dreavee.healthylifetracker.ui.screens.gym.GymLoggingScreen
import com.dreavee.healthylifetracker.ui.screens.water.WaterLoggingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(
                onNavigateToFood = { navController.navigate("food_logging") },
                onNavigateToWater = { navController.navigate("water_logging") },
                onNavigateToGym = { navController.navigate("gym_logging") },
                onNavigateToCalendar = { navController.navigate("calendar") },
                onNavigateToAnalytics = { navController.navigate("analytics") },
                onNavigateToAI = { navController.navigate("ai_insights") }
            )
        }
        composable("food_logging") {
            FoodLoggingScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("water_logging") {
            WaterLoggingScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("gym_logging") {
            GymLoggingScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("calendar") {
            CalendarScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("analytics") {
            AnalyticsScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("ai_insights") {
            AIInsightsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
