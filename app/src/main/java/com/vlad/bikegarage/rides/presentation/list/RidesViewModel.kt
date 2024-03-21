package com.vlad.bikegarage.rides.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.bikegarage.rides.domain.model.Ride
import com.vlad.bikegarage.rides.domain.use_case.DeleteRide
import com.vlad.bikegarage.rides.domain.use_case.GetRides
import com.vlad.bikegarage.util.convertMillisToDateMonthName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RidesViewModel @Inject constructor(
    private val getRides: GetRides,
    private val deleteRide: DeleteRide
) : ViewModel() {
    private val _state = MutableStateFlow(RidesState())
    val state = _state

    private var getRidesJob: Job? = null

    init {
        loadRides()
    }

    fun onEvent(event: RidesEvent) {
        when (event) {
            RidesEvent.OnAddRide -> {}
            RidesEvent.OnDeleteConfirmation -> {
                viewModelScope.launch {
                    deleteRide(_state.value.rideName)
                    _state.update { newState ->
                        newState.copy(showDialog = false)
                    }
                }
            }

            is RidesEvent.OnDeleteRide -> {
                _state.update { newState ->
                    newState.copy(rideName = event.rideName, showDialog = true)
                }
            }

            RidesEvent.OnDismissDialog -> {
                _state.update { newState ->
                    newState.copy(showDialog = false)
                }
            }
        }
    }

    private fun loadRides() {
        getRidesJob?.cancel()
        getRidesJob = getRides.invoke().onEach { rides: List<Ride> ->

            val groupedList = rides.groupBy {
                convertMillisToDateMonthName(it.date).substring(
                    3,
                    convertMillisToDateMonthName(it.date).lastIndexOf(".")
                )
            }
            groupedList.size
            _state.update { newState ->
                newState.copy(rides = groupedList)
            }
        }.launchIn(viewModelScope)

    }

}