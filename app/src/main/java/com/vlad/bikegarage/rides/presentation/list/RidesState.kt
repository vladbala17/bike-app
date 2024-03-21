package com.vlad.bikegarage.rides.presentation.list

import com.vlad.bikegarage.rides.domain.model.Ride
import com.vlad.bikegarage.rides.domain.model.RideChartRow

data class RidesState(
    val rides: Map<String, List<Ride>> = emptyMap(),
    val showDialog: Boolean = false,
    val rideStatistic: List<RideChartRow> = emptyList(),
    val totalKm: Int = 0,
    val rideName: String = "",
)
