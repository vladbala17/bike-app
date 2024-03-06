package com.vlad.bikegarage.bikes.presentation.detail

import androidx.compose.ui.graphics.Color
import com.vlad.bikegarage.bikes.domain.model.BikeType

data class BikeDetailState(
    val bikeId: String = "",
    val bikeType: BikeType = BikeType.Electric,
    val bikeColor: Color = Color.Red,
    val wheelSize: String = "",
    val remainingServiceKm: String = "",
    val totalRides: String = "",
    val totalRidesDistance: String = ""
)
