package com.dreavee.healthylifetracker.ui.screens.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreavee.healthylifetracker.HealthyLifeTrackerApplication
import com.dreavee.healthylifetracker.ui.ViewModelFactory
import com.dreavee.healthylifetracker.ui.theme.NeonGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodLoggingScreen(
    viewModel: FoodLoggingViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as HealthyLifeTrackerApplication).container)
    ),
    onBack: () -> Unit
) {
    var foodName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "Log Meal", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text("Food Name", color = Color.Gray) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = NeonGreen,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = calories,
                onValueChange = { calories = it },
                label = { Text("Calories", color = Color.Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = NeonGreen,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (foodName.isNotBlank() && calories.isNotBlank()) {
                        viewModel.logMeal(foodName, "Snack", calories.toIntOrNull() ?: 0)
                        onBack()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = NeonGreen),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Meal", color = Color.Black)
            }
        }
    }
}
