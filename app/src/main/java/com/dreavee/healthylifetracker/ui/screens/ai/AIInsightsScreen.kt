package com.dreavee.healthylifetracker.ui.screens.ai

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreavee.healthylifetracker.HealthyLifeTrackerApplication
import com.dreavee.healthylifetracker.ui.ViewModelFactory
import com.dreavee.healthylifetracker.domain.logic.Recommendation
import com.dreavee.healthylifetracker.domain.logic.RecommendationType
import com.dreavee.healthylifetracker.ui.components.NeonCard
import com.dreavee.healthylifetracker.ui.theme.NeonBlue
import com.dreavee.healthylifetracker.ui.theme.NeonGreen
import com.dreavee.healthylifetracker.ui.theme.NeonRed

@Composable
fun AIInsightsScreen(
    viewModel: AIInsightsViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as HealthyLifeTrackerApplication).container)
    ),
    onBack: () -> Unit
) {
    val recommendations by viewModel.recommendations.collectAsState()

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
                text = "AI Dietitian Insights",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(recommendations) { recommendation ->
                    RecommendationItem(recommendation)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun RecommendationItem(recommendation: Recommendation) {
    val color = when (recommendation.type) {
        RecommendationType.INFO -> NeonBlue
        RecommendationType.WARNING -> NeonRed
        RecommendationType.SUCCESS -> NeonGreen
        RecommendationType.TIP -> Color.Yellow
    }

    NeonCard(borderColor = color) {
        Text(
            text = recommendation.title,
            style = MaterialTheme.typography.titleMedium,
            color = color,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = recommendation.message,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}
