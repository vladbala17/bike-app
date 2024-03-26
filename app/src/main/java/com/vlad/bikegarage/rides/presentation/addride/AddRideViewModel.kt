package com.vlad.bikegarage.rides.presentation.addride

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.bikes.domain.use_case.GetBikeByName
import com.vlad.bikegarage.bikes.domain.use_case.GetBikes
import com.vlad.bikegarage.bikes.domain.use_case.GetRidesForBike
import com.vlad.bikegarage.bikes.domain.use_case.ValidateBikeName
import com.vlad.bikegarage.rides.domain.model.Ride
import com.vlad.bikegarage.rides.domain.use_case.AddRide
import com.vlad.bikegarage.rides.domain.use_case.GetRideDetail
import com.vlad.bikegarage.settings.domain.Preferences
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
    private val getBikes: GetBikes,
    private val preferences: Preferences,
    private val getRidesForBike: GetRidesForBike,
    private val getBikeByName: GetBikeByName
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
            viewModelScope.launch {
                val ride = getRideDetail(rideId = rideId)
                loadRide(ride)
            }
        }
    }

    fun onEvent(event: AddRideEvent) {
        when (event) {
            is AddRideEvent.OnBikeSelected -> {
                _state.update { newState ->
                    val getBikeByName =
                        _state.value.bikeNamesList.find { event.bikeName == it.name }
                    newState.copy(bikeName = event.bikeName, bikeType = getBikeByName!!.bikeType)
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


                    _state.update { newState ->
                        newState.copy(isValidatedSuccessfully = true, rideNameError = null)
                    }
                    val id = rideId
                    val rideTitle = _state.value.rideName
                    val bikeName = _state.value.bikeName
                    val bikeType = _state.value.bikeType
                    val durationHours = _state.value.durationHours
                    val durationMinutes = _state.value.durationMinutes
                    val distance = _state.value.distance
                    val date = _state.value.date

                    val ride = Ride(
                        rideName = rideTitle,
                        bikeName = bikeName,
                        bikeType = bikeType.type,
                        durationHours = durationHours,
                        durationMinutes = durationMinutes,
                        distance = distance.toInt(),
                        date = date,
                        id = id
                    )

                    viewModelScope.launch {
                        addRide(ride)
                    }
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
                newState.copy(bikeNamesList = bikes)
            }
        }.launchIn(viewModelScope)
    }

    private fun loadRide(ride: Ride) {
        _state.update { newState ->
            newState.copy(
                rideName = ride.rideName,
                bikeName = ride.bikeName,
                bikeType = BikeType.fromString(ride.bikeType),
                distance = ride.distance.toString(),
                durationHours = ride.durationHours,
                durationMinutes = ride.durationMinutes,
                date = ride.date
            )
        }
    }
}