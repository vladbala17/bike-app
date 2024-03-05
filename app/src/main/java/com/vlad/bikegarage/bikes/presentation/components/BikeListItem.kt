package com.vlad.bikegarage.bikes.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vlad.bikegarage.bikes.domain.model.Bike

@Composable
fun BikeListItem(
    bike: Bike,
    onEditBikeMenuClick: (Int) -> Unit,
    onDeleteBike: (String) -> Unit,
    modifier: Modifier
) {
    BikeCard(
        bikeId = bike.id,
        bikeName = bike.name,
        wheelSize = bike.wheelSize,
        bikeType = bike.bikeType,
        bikeColor = Color(bike.bikeColor),
        modifier = modifier,
        onEditBikeMenuClick = onEditBikeMenuClick,
        onDeleteBikeMenuClick = onDeleteBike
    )
}

//bike.serviceIn.toFloat() / (bike.serviceIn.toFloat() - bike.remainingServiceDistance.toFloat())
