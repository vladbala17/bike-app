package com.vlad.bikegarage.bikes.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vlad.bikegarage.bikes.domain.model.Bike

@Composable
fun BikeListItem(bike: Bike, modifier: Modifier) {
    BikeCard(
        bikeName = bike.name,
        wheelSize = bike.wheelSize,
        bikeType = bike.bikeType,
        modifier = modifier
    )
}

//bike.serviceIn.toFloat() / (bike.serviceIn.toFloat() - bike.remainingServiceDistance.toFloat())
