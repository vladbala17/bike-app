package com.vlad.bikegarage.bikes.presentation.list

import com.vlad.bikegarage.bikes.domain.model.Bike

data class BikesState(
    val bikes: List<Bike> = emptyList(),
    val bikeName: String = "",
    val showDialog: Boolean = false,
    val defaultUnit: String = ""
)