package com.vlad.bikegarage.bikes.presentation.addbikes

sealed class AddBikeEvent {
    data class Submit(val bikeName: String) : AddBikeEvent()
    data class OnColorPick(val color: Int): AddBikeEvent()
}