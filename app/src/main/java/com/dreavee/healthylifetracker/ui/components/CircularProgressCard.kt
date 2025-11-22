package com.dreavee.healthylifetracker.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressCard(
    percentage: Float,
    currentAmount: Int,
    targetAmount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(180.dp)
        ) {
            // Background circle
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    style = Stroke(width = 20f)
                )
            }
            
            // Progress arc
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawArc(
                    color = Color(0xFF2196F3),
                    startAngle = -90f,
                    sweepAngle = 360f * percentage,
                    useCenter = false,
                    style = Stroke(width = 20f, cap = StrokeCap.Round)
                )
            }
            
            // Center text
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${(percentage * 100).toInt()}%",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$currentAmount / $targetAmount ml",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
