package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.lifecycle.ViewModel
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
                    val list = _state.value.bikePagerList.toMutableList()
                    list[_state.value.selectedBike] =
                        list[_state.value.selectedBike].copy(color = event.color)
                    newState.copy(bikePagerList = list)
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
        _state.update { newState ->
            newState.copy(bikeTitle = _state.value.bikePagerList[page].title, selectedBike = page)
        }
    }
}