package com.dreavee.healthylifetracker.feature.settings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreavee.healthylifetracker.HealthyLifeTrackerApplication
import com.dreavee.healthylifetracker.core.util.FormatUtils
import com.dreavee.healthylifetracker.domain.model.AppTheme
import com.dreavee.healthylifetracker.domain.model.Gender
import com.dreavee.healthylifetracker.feature.settings.viewmodel.SettingsViewModel
import com.dreavee.healthylifetracker.ui.ViewModelFactory

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as HealthyLifeTrackerApplication).container)
    )
) {
    val settings by viewModel.settings.collectAsState()
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        // Profile Section
        SettingsSection(title = "Profile") {
            OutlinedTextField(
                value = settings.name,
                onValueChange = { viewModel.updateName(it) },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text("Gender", style = MaterialTheme.typography.labelMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Gender.values().forEach { gender ->
                    FilterChip(
                        selected = settings.gender == gender,
                        onClick = { viewModel.updateGender(gender) },
                        label = { Text(gender.name) }
                    )
                }
            }
        }
        
        // Water Section
        SettingsSection(title = "Water") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Daily Goal")
                Text("${settings.waterDailyGoalMl}ml", fontWeight = FontWeight.Bold)
            }
            
            Slider(
                value = settings.waterDailyGoalMl.toFloat(),
                onValueChange = { viewModel.updateWaterGoal(it.toInt()) },
                valueRange = 1000f..4000f,
                steps = 29
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Water Reminders")
                Switch(
                    checked = settings.waterReminderEnabled,
                    onCheckedChange = { viewModel.toggleWaterReminders(it) }
                )
            }
        }
        
        // Gym Section
        SettingsSection(title = "Gym / Workout") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Enable Gym Tracking")
                Switch(
                    checked = settings.gymTrackingEnabled,
                    onCheckedChange = { viewModel.toggleGymTracking(it) }
                )
            }
            
            if (settings.gymTrackingEnabled) {
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Weekly Target")
                    Text("${settings.gymWeeklyTargetSessions} sessions", fontWeight = FontWeight.Bold)
                }
                
                Slider(
                    value = settings.gymWeeklyTargetSessions.toFloat(),
                    onValueChange = { viewModel.updateGymWeeklyTarget(it.toInt()) },
                    valueRange = 1f..7f,
                    steps = 5
                )
            }
        }
        
        // Meals Section
        SettingsSection(title = "Meals") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Enable Meal Tracking")
                Switch(
                    checked = settings.mealsTrackingEnabled,
                    onCheckedChange = { viewModel.toggleMealsTracking(it) }
                )
            }
        }
        
        // App Section
        SettingsSection(title = "App") {
            Text("Theme", style = MaterialTheme.typography.labelMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AppTheme.values().forEach { theme ->
                    FilterChip(
                        selected = settings.theme == theme,
                        onClick = { viewModel.updateTheme(theme) },
                        label = { Text(theme.name) }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("24-hour Format")
                Switch(
                    checked = settings.use24hFormat,
                    onCheckedChange = { viewModel.toggle24hFormat(it) }
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Divider()
            content()
        }
    }
}
