package com.vlad.bikegarage.rides.presentation.detail

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel(assistedFactory = RideDetailViewModel.RideDetailViewModelFactory::class)
class RideDetailViewModel @AssistedInject constructor(@Assisted rideId: Int) : ViewModel() {

    private val _state = MutableStateFlow(RideDetailState())
    val state = _state

    @AssistedFactory
    interface RideDetailViewModelFactory {
        fun create(rideId: Int): RideDetailViewModel
    }

    fun onEvent(event: RideDetailState) {

    }
}