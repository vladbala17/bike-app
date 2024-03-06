package com.vlad.bikegarage.bikes.presentation.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class BikeDetailViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(BikeDetailState())
    val state = _state


    fun onEvent(event: BikesDetailEvent) {

    }
}