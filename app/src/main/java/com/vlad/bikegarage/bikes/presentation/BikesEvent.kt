package com.vlad.bikegarage.bikes.presentation

sealed class BikesEvent {
    object OnAddBike: BikesEvent()
}