package com.dreavee.healthylifetracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dreavee.healthylifetracker.domain.logic.EventType
import com.dreavee.healthylifetracker.domain.logic.TimelineEvent
import com.dreavee.healthylifetracker.ui.theme.NeonBlue
import com.dreavee.healthylifetracker.ui.theme.NeonGreen
import com.dreavee.healthylifetracker.ui.theme.NeonRed
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TimelineBar(
    events: List<TimelineEvent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Next Healthy Actions",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(events) { event ->
                TimelineItem(event)
            }
        }
    }
}

@Composable
fun TimelineItem(event: TimelineEvent) {
    val color = when (event.type) {
        EventType.WATER -> NeonBlue
        EventType.FOOD -> NeonGreen
        EventType.WORKOUT -> NeonRed
    }

    val timeString = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(event.time))

    NeonCard(
        modifier = Modifier.width(160.dp),
        borderColor = color
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = timeString,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = event.message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}
