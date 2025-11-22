package com.dreavee.healthylifetracker.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dreavee.healthylifetracker.domain.logic.TimelinePredictor
import com.dreavee.healthylifetracker.util.NotificationHelper


class TimelineWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val timelinePredictor: TimelinePredictor
    private val notificationHelper: NotificationHelper

    init {
        val appContainer = (context.applicationContext as com.dreavee.healthylifetracker.HealthyLifeTrackerApplication).container
        timelinePredictor = appContainer.timelinePredictor
        notificationHelper = appContainer.notificationHelper
    }

    override suspend fun doWork(): Result {
        val events = timelinePredictor.getTimelineEvents(System.currentTimeMillis())
        
        // Simple logic: Notify for the first actionable event
        val nextActionableEvent = events.firstOrNull { it.isActionable }
        
        if (nextActionableEvent != null) {
            notificationHelper.showNotification(
                title = "Healthy Life Tracker",
                message = nextActionableEvent.message
            )
        }

        return Result.success()
    }
}
