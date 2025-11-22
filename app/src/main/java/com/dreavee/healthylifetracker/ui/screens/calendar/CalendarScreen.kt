package com.dreavee.healthylifetracker.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreavee.healthylifetracker.HealthyLifeTrackerApplication
import com.dreavee.healthylifetracker.ui.ViewModelFactory
import com.dreavee.healthylifetracker.ui.components.NeonCard
import com.dreavee.healthylifetracker.ui.theme.NeonGreen
import com.dreavee.healthylifetracker.ui.theme.NeonPurple
import java.util.Calendar
import java.util.Locale

@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as HealthyLifeTrackerApplication).container)
    ),
    onBack: () -> Unit
) {
    val streakData by viewModel.streakData.collectAsState()

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
                text = "Your Progress",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Streak Card
            NeonCard(borderColor = NeonPurple) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Current Streak", color = Color.Gray)
                        Text(
                            text = "${streakData?.currentStreak ?: 0} Days",
                            style = MaterialTheme.typography.headlineLarge,
                            color = NeonGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    // Placeholder for flame icon or similar
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(NeonPurple.copy(alpha = 0.2f), CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) ?: "",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Simple Calendar Grid (Placeholder for full calendar logic)
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Days of week headers
                val days = listOf("S", "M", "T", "W", "T", "F", "S")
                items(7) { index ->
                    Text(
                        text = days[index],
                        color = Color.Gray,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Mock days (1-30)
                items(30) { index ->
                    val day = index + 1
                    val isToday = day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                    
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                if (isToday) NeonGreen else Color.DarkGray.copy(alpha = 0.3f),
                                CircleShape
                            )
                    ) {
                        Text(
                            text = day.toString(),
                            color = if (isToday) Color.Black else Color.White
                        )
                    }
                }
            }
        }
    }
}
