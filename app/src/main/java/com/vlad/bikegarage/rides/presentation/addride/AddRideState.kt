package com.vlad.bikegarage.rides.presentation.addride

import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.util.UiText

data class AddRideState(
    val rideName: String = "",
    val bikeName: String = "",
    val bikeType: BikeType = BikeType.RoadBike,
    val distance: String = "",
    val durationHours: Int = 2,
    val durationMinutes: Int = 24,
    val date: Long = System.currentTimeMillis(),
    val rideNameError: UiText? = null,
    val distanceError: UiText? = null,
    val bikeNamesList: List<Bike> = emptyList(),
    val showDurationPicker: Boolean = false,
    val showDatePicker: Boolean = false,
    val isValidatedSuccessfully: Boolean = false
)
