package com.dreavee.healthylifetracker.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreavee.healthylifetracker.HealthyLifeTrackerApplication
import com.dreavee.healthylifetracker.ui.ViewModelFactory
import com.dreavee.healthylifetracker.ui.components.TimelineBar

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as HealthyLifeTrackerApplication).container)
    ),
    onNavigateToFood: () -> Unit,
    onNavigateToWater: () -> Unit,
    onNavigateToGym: () -> Unit,
    onNavigateToCalendar: () -> Unit,
    onNavigateToAnalytics: () -> Unit,
    onNavigateToAI: () -> Unit
) {
    val timelineEvents by viewModel.timelineEvents.collectAsState()

    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TimelineBar(events = timelineEvents)
            
            // TODO: Add Quick Actions and Stats
            Spacer(modifier = Modifier.height(16.dp))
            
            // Hydration Meter Placeholder (Should be connected to real data)
            // For now, hardcoding for demo
             Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                com.dreavee.healthylifetracker.ui.components.CircularHydrationMeter(
                    percentage = 0.4f,
                    currentAmount = 800,
                    targetAmount = 2000
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            androidx.compose.material3.Button(
                onClick = onNavigateToCalendar,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = com.dreavee.healthylifetracker.ui.theme.NeonPurple)
            ) {
                Text("View Calendar & Streaks", color = Color.White)
            }

            androidx.compose.material3.Button(
                onClick = onNavigateToAnalytics,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = com.dreavee.healthylifetracker.ui.theme.NeonBlue)
            ) {
                Text("View Analytics", color = Color.White)
            }

            androidx.compose.material3.Button(
                onClick = onNavigateToAI,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = com.dreavee.healthylifetracker.ui.theme.NeonGreen)
            ) {
                Text("AI Dietitian Insights", color = Color.Black)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
