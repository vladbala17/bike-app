package com.vlad.bikegarage.rides.presentation.addride

import com.vlad.bikegarage.util.UiText

data class AddRideState(
    val rideName: String = "",
    val bikeName: String = "",
    val distance: String = "",
    val durationHours: Int = 2,
    val durationMinutes: Int = 24,
    val date: Long = System.currentTimeMillis(),
    val rideNameError: UiText? = null,
    val bikeNamesList: List<String> = emptyList(),
    val showDurationPicker: Boolean = false,
    val showDatePicker: Boolean = false,
    val isValidatedSuccessfully: Boolean = false
)
