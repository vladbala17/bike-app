package com.vlad.bikegarage.settings.presentation

sealed class SettingsEvent {
    data class OnDistanceUnitSet(val unit: String) : SettingsEvent()
    data class OnServiceReminderSet(val distanceReminder: String) : SettingsEvent()
    object OnNotifyReminder : SettingsEvent()
    data class OnDefaultBikeSet(val bike: String) : SettingsEvent()
}