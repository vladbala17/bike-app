package com.vlad.bikegarage.rides.presentation.addride

sealed class AddRideEvent() {
    data class OnRideTitleAdded(val rideTitle: String) : AddRideEvent()
    data class OnBikeSelected(val bikeName: String) : AddRideEvent()
    data class OnRideDistanceAdded(val distance: String) : AddRideEvent()
    data class OnRideDurationAdded(val duration: String) : AddRideEvent()
    data class OnRideDateAdded(val date: Long) : AddRideEvent()
    object Submit : AddRideEvent()

}
