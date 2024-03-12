package com.vlad.bikegarage.rides.presentation.addride

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.use_case.GetBikes
import com.vlad.bikegarage.bikes.domain.use_case.ValidateBikeName
import com.vlad.bikegarage.rides.domain.model.Ride
import com.vlad.bikegarage.rides.domain.use_case.AddRide
import com.vlad.bikegarage.rides.domain.use_case.GetRideDetail
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = AddRideViewModel.AddRideViewModelFactory::class)
class AddRideViewModel @AssistedInject constructor(
    @Assisted val rideId: Int,
    private val rideNameUseCase: ValidateBikeName,
    private val addRide: AddRide,
    private val getRideDetail: GetRideDetail,
    private val getBikes: GetBikes
) :
    ViewModel() {
    private val _state = MutableStateFlow(AddRideState())
    val state = _state

    private var getRidesJob: Job? = null

    @AssistedFactory
    interface AddRideViewModelFactory {
        fun create(id: Int): AddRideViewModel
    }

    init {
        loadBikesNames()
        if (rideId > 0) {
            val ride = viewModelScope.launch { getRideDetail(rideId = rideId) }
        }
    }

    fun onEvent(event: AddRideEvent) {
        when (event) {
            is AddRideEvent.OnBikeSelected -> {
                _state.update { newState ->
                    newState.copy(bikeName = event.bikeName)
                }
            }

            is AddRideEvent.OnRideDateAdded -> {}
            is AddRideEvent.OnRideDistanceAdded -> {
                _state.update { newState ->
                    newState.copy(distance = event.distance)
                }
            }

            is AddRideEvent.OnRideDurationAdded -> {}
            is AddRideEvent.OnRideTitleAdded -> {
                _state.update { newState ->
                    newState.copy(rideName = event.rideTitle)
                }
            }

            AddRideEvent.Submit -> {
                if (rideNameIsValid()) {
                    val id = rideId
                    val rideTitle = _state.value.rideName
                    val bikeName = _state.value.bikeName
                    val duration = _state.value.duration
                    val distance = _state.value.distance
                    val date = _state.value.date

                    val ride = Ride(
                        rideName = rideTitle,
                        bikeName = bikeName,
                        duration = duration,
                        distance = distance,
                        date = Long.MAX_VALUE,
                        id = id
                    )

                    viewModelScope.launch { addRide(ride) }
                }
            }

            AddRideEvent.OnDurationClicked -> {
                _state.update { newState ->
                    newState.copy(showDurationPicker = true)
                }
            }

            AddRideEvent.OnDismissDurationPicker -> {
                _state.update { newState ->
                    newState.copy(showDurationPicker = false)
                }
            }

            is AddRideEvent.OnDurationSet -> {
                _state.update { newState ->
                    newState.copy(
                        durationHours = event.hours,
                        durationMinutes = event.minutes,
                        showDurationPicker = false
                    )
                }
            }

            AddRideEvent.OnDateClicked -> {
                _state.update { newState ->
                    newState.copy(showDatePicker = true)
                }
            }

            is AddRideEvent.OnDateSet -> {
                _state.update { newState ->
                    newState.copy(date = event.date, showDatePicker = false)
                }
            }

            AddRideEvent.OnDismissDatePicker -> {
                _state.update { newState ->
                    newState.copy(showDatePicker = false)
                }
            }
        }
    }

    private fun rideNameIsValid(): Boolean {
        val rideNameValidation = rideNameUseCase(_state.value.rideName)
        _state.update { newState ->
            newState.copy(rideNameError = rideNameValidation.errorMessage)
        }

        return rideNameValidation.successful
    }

    private fun loadBikesNames() {
        getRidesJob?.cancel()
        getRidesJob = getBikes.invoke().onEach { bikes: List<Bike> ->
            _state.update { newState ->
                newState.copy(bikeNamesList = bikes.map { bike -> bike.name })
            }
        }.launchIn(viewModelScope)
    }
}