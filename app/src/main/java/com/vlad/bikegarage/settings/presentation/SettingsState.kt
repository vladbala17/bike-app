package com.vlad.bikegarage.settings.presentation

data class SettingsState(
    val distanceUnit: String = "Km",
    val serviceIntervalReminder: String = "100",
    val isServiceNotifyEnabled: Boolean = true,
    val defaultBike: String = "TestBike",
    val defaultBikeList: List<String> = emptyList()
)