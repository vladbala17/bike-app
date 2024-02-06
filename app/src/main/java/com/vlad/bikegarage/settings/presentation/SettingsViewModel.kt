package com.vlad.bikegarage.settings.presentation

import androidx.lifecycle.ViewModel
import com.vlad.bikegarage.settings.domain.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        loadSettings()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnDefaultBikeSet -> TODO()
            is SettingsEvent.OnDistanceUnitSet -> TODO()
            is SettingsEvent.OnNotifyReminder -> TODO()
            is SettingsEvent.OnServiceReminderSet -> TODO()
        }
    }

    private fun loadSettings() {
        _state.update {
            it.copy(
                distanceUnit = preferences.getDistanceUnit(),
                serviceReminder = preferences.getServiceInterval(),
                isServiceNotifyEnabled = preferences.areNotificationsEnabled(),
                defaultBike = preferences.getDefaultBikeName()
            )
        }
    }
}