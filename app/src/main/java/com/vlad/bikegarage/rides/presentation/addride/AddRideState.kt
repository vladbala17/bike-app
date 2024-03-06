package com.vlad.bikegarage.rides.presentation.addride

import com.vlad.bikegarage.util.UiText

data class AddRideState(
    val rideName: String = "",
    val bikeName: String = "",
    val distance: String = "",
    val duration: String = "",
    val date: String = "",
    val rideNameError: UiText? = null,
    val bikeNamesList: List<String> = emptyList()
)
