package com.dreavee.healthylifetracker.ui.components

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.dreavee.healthylifetracker.ui.theme.NeonBlue
import com.dreavee.healthylifetracker.ui.theme.NeonGreen
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

@Composable
fun WeeklyWaterChart(
    dataPoints: List<Float>, // 7 days of data
    modifier: Modifier = Modifier
) {
    val neonBlueArgb = NeonBlue.toArgb()
    val neonGreenArgb = NeonGreen.toArgb()

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                legend.isEnabled = false
                setDrawGridBackground(false)
                setTouchEnabled(false)
                
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.textColor = AndroidColor.WHITE
                
                axisLeft.textColor = AndroidColor.WHITE
                axisRight.isEnabled = false
                
                animateY(1000)
            }
        },
        update = { chart ->
            val entries = dataPoints.mapIndexed { index, value ->
                BarEntry(index.toFloat(), value)
            }
            
            val dataSet = BarDataSet(entries, "Water Intake").apply {
                color = neonBlueArgb
                valueTextColor = AndroidColor.WHITE
                valueTextSize = 10f
            }
            
            chart.data = BarData(dataSet)
            chart.invalidate()
        }
    )
}
