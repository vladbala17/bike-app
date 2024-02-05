package com.vlad.bikegarage.settings.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel: ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()


    fun onEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.OnDefaultBikeSet -> TODO()
            is SettingsEvent.OnDistanceUnitSet -> TODO()
            is SettingsEvent.OnNotifyReminder -> TODO()
            is SettingsEvent.OnServiceReminderSet -> TODO()
        }
    }
}