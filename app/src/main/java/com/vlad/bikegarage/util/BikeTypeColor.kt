package com.vlad.bikegarage.util

import androidx.compose.ui.graphics.Color
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.ui.theme.Orange
import com.vlad.bikegarage.ui.theme.Red
import com.vlad.bikegarage.ui.theme.White
import com.vlad.bikegarage.ui.theme.Yellow

fun getColorByType(bikeType: BikeType): Color {
    return when (bikeType) {
        BikeType.Electric -> White
        BikeType.Hybrid -> Yellow
        BikeType.MTB -> Orange
        BikeType.RoadBike -> Red
    }
}