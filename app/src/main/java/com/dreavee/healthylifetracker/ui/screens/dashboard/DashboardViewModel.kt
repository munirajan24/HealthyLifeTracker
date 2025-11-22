package com.dreavee.healthylifetracker.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.domain.logic.TimelineEvent
import com.dreavee.healthylifetracker.domain.logic.TimelinePredictor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DashboardViewModel(
    private val trackerRepository: com.dreavee.healthylifetracker.data.repository.TrackerRepository,
    private val timelinePredictor: TimelinePredictor,
    private val streakManager: com.dreavee.healthylifetracker.domain.logic.StreakManager,
    private val recommendationEngine: com.dreavee.healthylifetracker.domain.logic.RecommendationEngine
) : ViewModel() {

    private val _timelineEvents = MutableStateFlow<List<TimelineEvent>>(emptyList())
    val timelineEvents: StateFlow<List<TimelineEvent>> = _timelineEvents.asStateFlow()

    init {
        refreshTimeline()
    }

    fun refreshTimeline() {
        viewModelScope.launch {
            val events = timelinePredictor.getTimelineEvents(System.currentTimeMillis())
            _timelineEvents.value = events
        }
    }
}
