package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.lifecycle.ViewModel
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.bikes.domain.use_case.ValidateBikeName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddBikeViewModel @Inject constructor(private val bikeNameUseCase: ValidateBikeName) :
    ViewModel() {
    private val _state = MutableStateFlow(AddBikeState())
    val state = _state.asStateFlow()


    fun onEvent(event: AddBikeEvent) {
        when (event) {
            is AddBikeEvent.Submit -> {
                validateBikeName()
            }

            is AddBikeEvent.OnColorPick -> {
                _state.update { newState ->
                    newState.copy(bikeColor = event.color)
                }

            }

            is AddBikeEvent.OnPageSelected -> {
                selectTypeFromPage(event.page)
            }
        }
    }

    private fun validateBikeName(): Boolean {
        val bikeNameValidation = bikeNameUseCase(_state.value.bikeName)
        _state.update { newState ->
            newState.copy(bikeNameError = bikeNameValidation.errorMessage)
        }

        return bikeNameValidation.successful
    }

    private fun selectTypeFromPage(page: Int) {
        when (page) {
            0 -> {
                _state.update { newState ->
                    newState.copy(bikeType = BikeType.Electric, bikeTitle = BikeType.Electric.type)
                }
            }

            1 -> {
                _state.update { newState ->
                    newState.copy(bikeType = BikeType.MTB, bikeTitle = BikeType.MTB.type)
                }
            }

            2 -> {
                _state.update { newState ->
                    newState.copy(bikeType = BikeType.Hybrid, bikeTitle = BikeType.Hybrid.type)
                }
            }

            3 -> {
                _state.update { newState ->
                    newState.copy(bikeType = BikeType.RoadBike, bikeTitle = BikeType.RoadBike.type)
                }
            }
        }
    }
}