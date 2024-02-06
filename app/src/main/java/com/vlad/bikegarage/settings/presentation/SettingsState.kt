package com.vlad.bikegarage.settings.presentation

data class SettingsState(
    val distanceUnit: String = "km",
    val serviceReminder: Int = 100,
    val isServiceNotifyEnabled: Boolean = true,
    val defaultBike: String = "TestBike"
)