package com.vlad.bikegarage.rides.presentation.list

import com.vlad.bikegarage.rides.domain.model.Ride

data class RidesState(
    val rides: List<Ride> = emptyList(),
    val showDialog: Boolean = false,
    val rideName: String = "",
)
