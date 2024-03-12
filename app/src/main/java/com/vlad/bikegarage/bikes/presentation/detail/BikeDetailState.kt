package com.vlad.bikegarage.bikes.presentation.detail

import androidx.compose.ui.graphics.Color
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.rides.domain.model.Ride

data class BikeDetailState(
    val bikeId: String = "",
    val bikeType: BikeType = BikeType.Electric,
    val bikeColor: Color = Color.Red,
    val wheelSize: String = "",
    val remainingServiceKm: String = "",
    val totalRides: Int = 0,
    val totalRidesDistance: Int = 0,
    val rideList: List<Ride> = emptyList()
)
