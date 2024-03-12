package com.vlad.bikegarage.rides.domain.model

data class Ride(
    val id: Int,
    val rideName: String,
    val bikeName: String,
    val distance: String,
    val durationHours: Int,
    val durationMinutes: Int,
    val date: Long
)
