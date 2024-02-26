package com.vlad.bikegarage.bikes.domain.model

data class Bike(
    val name: String,
    val wheelSize: String,
    val serviceIn: String,
    val isDefault: Boolean,
    val bikeType: BikeType,
    val bikeColor: Int
)