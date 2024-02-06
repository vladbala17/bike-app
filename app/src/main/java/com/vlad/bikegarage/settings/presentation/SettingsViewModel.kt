package com.vlad.bikegarage.settings.presentation

import androidx.lifecycle.ViewModel
import com.vlad.bikegarage.settings.domain.Preferences
import com.vlad.bikegarage.settings.domain.use_vase.FilterOutDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        loadSettings()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnDefaultBikeSet -> TODO()
            is SettingsEvent.OnDistanceUnitSet -> {

            }

            is SettingsEvent.OnNotifyReminder -> TODO()
            is SettingsEvent.OnServiceIntervalReminderSet -> {
                _state.update { newState ->
                    newState.copy(serviceIntervalReminder = filterOutDigits(event.distanceIntervalReminder))
                }
                preferences.saveServiceInterval(filterOutDigits(event.distanceIntervalReminder))
            }
        }
    }

    private fun loadSettings() {
        _state.update {
            it.copy(
                distanceUnit = preferences.getDistanceUnit(),
                serviceIntervalReminder = preferences.getServiceInterval(),
                isServiceNotifyEnabled = preferences.areNotificationsEnabled(),
                defaultBike = preferences.getDefaultBikeName()
            )
        }
    }
}