package com.vlad.bikegarage.bikes.presentation.detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.bikegarage.bikes.domain.use_case.GetBikeDetail
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = BikeDetailViewModel.BikeDetailViewModelFactory::class)
class BikeDetailViewModel @AssistedInject constructor(
    @Assisted val bikeId: Int,
    getBikeDetail: GetBikeDetail
) : ViewModel() {
    private val _state = MutableStateFlow(BikeDetailState())
    val state = _state

    @AssistedFactory
    interface BikeDetailViewModelFactory {
        fun create(bikeId: Int): BikeDetailViewModel
    }

    init {
        if (bikeId > 0) {
            viewModelScope.launch {
                val bike = getBikeDetail(bikeId)
                _state.update { newState ->
                    newState.copy(
                        bikeColor = Color(bike.bikeColor),
                        wheelSize = bike.wheelSize,
                        bikeType = bike.bikeType
                    )
                }

            }
        }
    }

    fun onEvent(event: BikesDetailEvent) {

    }
}