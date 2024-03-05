package com.vlad.bikegarage.bikes.presentation

sealed class BikesEvent {
    object OnAddBike: BikesEvent()
    data class OnDeleteBike(val bikeName: String): BikesEvent()
}