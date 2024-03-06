package com.vlad.bikegarage.rides.presentation.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RidesViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(RidesState())
    val state = _state


    fun onEvent(event: RidesEvent) {

    }

}