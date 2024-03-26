package com.vlad.bikegarage.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.use_case.GetBikes
import com.vlad.bikegarage.settings.domain.Preferences
import com.vlad.bikegarage.settings.domain.use_vase.FilterOutDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val getBikes: GetBikes
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    private var getBikesJob: Job? = null

    init {
        loadSettings()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnDefaultBikeSet -> {
                _state.update { newState ->
                    newState.copy(defaultBike = event.bike)
                }
                preferences.saveDefaultBike(event.bike)
            }

            is SettingsEvent.OnDistanceUnitSet -> {
                _state.update { newState ->
                    newState.copy(distanceUnit = event.unit)
                }
                preferences.saveDistanceUnit(event.unit)
            }

            is SettingsEvent.OnNotifyReminder -> {
                _state.update { newState ->
                    newState.copy(isServiceNotifyEnabled = event.notifyReminder)
                }
                preferences.saveEnabledNotifications(_state.value.isServiceNotifyEnabled)
            }

            is SettingsEvent.OnServiceIntervalReminderSet -> {
                _state.update { newState ->

                    //Use case not needed because the value is already a number
                    newState.copy(serviceIntervalReminder = filterOutDigits(event.distanceIntervalReminder.toString()))
                }
                preferences.saveServiceInterval(filterOutDigits(event.distanceIntervalReminder.toString()))
            }

            SettingsEvent.OnShowPermissionDialog -> {
                _state.update { newState ->
                    newState.copy(showPermissionDialog = true)
                }
            }
            SettingsEvent.OnDismissPermissionDialog -> {
                _state.update { newState ->
                    newState.copy(showPermissionDialog = false)
                }
            }
        }
    }

    private fun loadSettings() {
        getBikesJob?.cancel()
        getBikesJob = getBikes.invoke().onEach { bikes: List<Bike> ->
            val bikeNames = bikes.map { it.name }
            _state.update {
                it.copy(
                    distanceUnit = preferences.getDistanceUnit(),
                    serviceIntervalReminder = preferences.getServiceInterval(),
                    isServiceNotifyEnabled = preferences.areNotificationsEnabled(),
                    defaultBike = preferences.getDefaultBikeName(),
                    defaultBikeList = bikeNames
                )
            }
        }.launchIn(viewModelScope)
    }
}