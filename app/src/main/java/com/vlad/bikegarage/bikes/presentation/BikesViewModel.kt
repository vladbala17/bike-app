package com.vlad.bikegarage.bikes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.use_case.GetBikes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BikesViewModel @Inject constructor(private val getBikes: GetBikes) : ViewModel() {
    private val _state = MutableStateFlow(BikesState())
    val state = _state.asStateFlow()

    private var getBikesJob: Job? = null

    init {
        loadBikes()
    }

    fun onEvent(event: BikesEvent) {
        when (event) {
            BikesEvent.OnAddBike -> {

            }
        }
    }

    private fun loadBikes() {
        getBikesJob?.cancel()
        getBikesJob = getBikes.invoke().onEach { bikes: List<Bike> ->
            _state.update { newState ->
                newState.copy(bikes = bikes)
            }
        }.launchIn(viewModelScope)
    }
}