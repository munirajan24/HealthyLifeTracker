package com.dreavee.healthylifetracker.ui.screens.water

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreavee.healthylifetracker.HealthyLifeTrackerApplication
import com.dreavee.healthylifetracker.ui.ViewModelFactory
import com.dreavee.healthylifetracker.ui.theme.NeonBlue
import com.dreavee.healthylifetracker.ui.theme.NeonRed
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WaterLoggingScreen(
    viewModel: WaterLoggingViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as HealthyLifeTrackerApplication).container)
    ),
    onBack: () -> Unit
) {
    val canDrink by viewModel.canDrink.collectAsState()
    val nextSafeTime by viewModel.nextSafeTime.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checkWaterStatus()
    }

    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (canDrink) {
                Text(
                    text = "Hydrate Now",
                    style = MaterialTheme.typography.headlineLarge,
                    color = NeonBlue
                )
                Spacer(modifier = Modifier.height(32.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    WaterButton(amount = 250, onClick = {
                        viewModel.logWater(250)
                        onBack()
                    })
                    WaterButton(amount = 500, onClick = {
                        viewModel.logWater(500)
                        onBack()
                    })
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Wait",
                    tint = NeonRed,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Wait before drinking!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = NeonRed,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                val timeString = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(nextSafeTime))
                Text(
                    text = "Safe time: $timeString",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Digestion in progress...",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text("Back to Dashboard", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun WaterButton(amount: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(120.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = NeonBlue.copy(alpha = 0.2f))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = androidx.compose.ui.res.painterResource(android.R.drawable.ic_menu_mylocation), // Placeholder icon
                contentDescription = null,
                tint = NeonBlue
            )
            Text(text = "${amount}ml", color = NeonBlue, fontWeight = FontWeight.Bold)
        }
    }
}
