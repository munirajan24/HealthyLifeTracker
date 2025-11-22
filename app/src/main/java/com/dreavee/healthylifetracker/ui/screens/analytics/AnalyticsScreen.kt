package com.dreavee.healthylifetracker.ui.screens.analytics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreavee.healthylifetracker.HealthyLifeTrackerApplication
import com.dreavee.healthylifetracker.ui.ViewModelFactory
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dreavee.healthylifetracker.ui.components.NeonCard
import com.dreavee.healthylifetracker.ui.components.WeeklyWaterChart
import com.dreavee.healthylifetracker.ui.theme.NeonBlue

@Composable
fun AnalyticsScreen(
    viewModel: AnalyticsViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as HealthyLifeTrackerApplication).container)
    ),
    onBack: () -> Unit
) {
    val waterData by viewModel.weeklyWaterData.collectAsState()

    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Weekly Analytics",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Water Intake (Last 7 Days)",
                style = MaterialTheme.typography.titleMedium,
                color = NeonBlue
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            NeonCard {
                WeeklyWaterChart(dataPoints = waterData)
            }
            
            // TODO: Add more charts for Food/Gym
        }
    }
}
