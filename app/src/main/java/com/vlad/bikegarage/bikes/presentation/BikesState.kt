package com.vlad.bikegarage.bikes.presentation

import com.vlad.bikegarage.bikes.domain.model.Bike

data class BikesState(
    val bikes: List<Bike> = emptyList()
)