package com.vlad.bikegarage.bikes.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.use_case.DeleteBike
import com.vlad.bikegarage.bikes.domain.use_case.GetBikes
import com.vlad.bikegarage.bikes.domain.use_case.GetRidesForBike
import com.vlad.bikegarage.settings.domain.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikesViewModel @Inject constructor(
    private val getBikes: GetBikes,
    private val deleteBike: DeleteBike,
    private val getRidesForBike: GetRidesForBike,
    private val preferences: Preferences
) : ViewModel() {
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

            is BikesEvent.OnDeleteBike -> {
                _state.update { newState ->
                    newState.copy(bikeName = event.bikeName, showDialog = true)
                }
            }

            BikesEvent.OnDeleteConfirmation -> {
                viewModelScope.launch {
                    deleteBike(_state.value.bikeName)
                    _state.update { newState ->
                        newState.copy(showDialog = false)
                    }
                }
            }

            BikesEvent.OnDismissDialog -> {
                _state.update { newState ->
                    newState.copy(showDialog = false)
                }
            }
        }
    }

    private fun loadBikes() {
        getBikesJob?.cancel()
        getBikesJob = getBikes.invoke().onEach { bikes: List<Bike> ->
            val bikeRidesList = bikes.map { bike: Bike ->
                val rideList = getRidesForBike(bike.name)
                val totalDistance = rideList.sumOf { it.distance }
                bike.copy(
                    remainingServiceDistance = bike.serviceIn - totalDistance,
                    usageUntilService = totalDistance.toFloat() / bike.serviceIn.toFloat()
                )
            }
            _state.update { newState ->
                newState.copy(bikes = bikeRidesList, defaultUnit = preferences.getDistanceUnit())
            }
        }.launchIn(viewModelScope)
    }
}