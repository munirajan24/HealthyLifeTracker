package com.dreavee.healthylifetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dreavee.healthylifetracker.ui.navigation.AppNavigation
import com.dreavee.healthylifetracker.ui.theme.HealthyLifeTrackerTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Schedule Background Work
        val workRequest = androidx.work.PeriodicWorkRequestBuilder<com.dreavee.healthylifetracker.workers.TimelineWorker>(
            15, java.util.concurrent.TimeUnit.MINUTES
        ).build()
        
        androidx.work.WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "TimelineWorker",
            androidx.work.ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )

        setContent {
            HealthyLifeTrackerTheme {
                AppNavigation()
            }
        }
    }
}