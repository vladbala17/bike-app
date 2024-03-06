package com.vlad.bikegarage.rides.presentation.addride

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddRideViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(AddRideState())
    val state = _state


    fun onEvent(event: AddRideEvent) {

    }
}