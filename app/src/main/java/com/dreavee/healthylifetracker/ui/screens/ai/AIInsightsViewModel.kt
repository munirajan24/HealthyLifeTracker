package com.dreavee.healthylifetracker.ui.screens.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreavee.healthylifetracker.domain.logic.Recommendation
import com.dreavee.healthylifetracker.domain.logic.RecommendationEngine

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AIInsightsViewModel(
    private val recommendationEngine: RecommendationEngine
) : ViewModel() {

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    init {
        loadInsights()
    }

    fun loadInsights() {
        viewModelScope.launch {
            _recommendations.value = recommendationEngine.getRecommendations()
        }
    }
}
