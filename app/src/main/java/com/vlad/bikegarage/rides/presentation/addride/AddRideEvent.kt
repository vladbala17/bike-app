package com.vlad.bikegarage.rides.presentation.addride

sealed class AddRideEvent() {
    data class OnRideTitleAdded(val rideTitle: String) : AddRideEvent()
    data class OnBikeSelected(val bikeName: String) : AddRideEvent()
    data class OnRideDistanceAdded(val distance: String) : AddRideEvent()
    data class OnRideDurationAdded(val duration: String) : AddRideEvent()
    data class OnRideDateAdded(val date: Long) : AddRideEvent()
    object OnDurationClicked : AddRideEvent()
    object OnDismissDurationPicker : AddRideEvent()
    data class OnDurationSet(val hours: Int, val minutes: Int) : AddRideEvent()
    object Submit : AddRideEvent()

}
