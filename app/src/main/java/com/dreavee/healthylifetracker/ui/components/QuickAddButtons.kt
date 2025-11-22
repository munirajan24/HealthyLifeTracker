package com.dreavee.healthylifetracker.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuickAddButtons(
    onAddWater: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = { onAddWater(200) },
            modifier = Modifier.weight(1f)
        ) {
            Text("+200ml")
        }
        
        Button(
            onClick = { onAddWater(250) },
            modifier = Modifier.weight(1f)
        ) {
            Text("+250ml")
        }
        
        OutlinedButton(
            onClick = { onAddWater(300) },
            modifier = Modifier.weight(1f)
        ) {
            Text("+300ml")
        }
    }
}
