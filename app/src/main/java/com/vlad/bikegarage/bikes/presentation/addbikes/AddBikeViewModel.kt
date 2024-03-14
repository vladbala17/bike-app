package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.use_case.AddBike
import com.vlad.bikegarage.bikes.domain.use_case.GetBikeDetail
import com.vlad.bikegarage.bikes.domain.use_case.ValidateBikeName
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = AddBikeViewModel.AddBikeViewModelFactory::class)
class AddBikeViewModel @AssistedInject constructor(
    @Assisted val bikeId: Int,
    private val bikeNameUseCase: ValidateBikeName,
    private val addBike: AddBike,
    private val getBikeDetail: GetBikeDetail
) : ViewModel() {

    @AssistedFactory
    interface AddBikeViewModelFactory {
        fun create(id: Int): AddBikeViewModel
    }

    init {
        if (bikeId > 0) {
            viewModelScope.launch {
                val bike = getBikeDetail(bikeId)
                loadBike(bike)
            }
        }
    }

    private val _state = MutableStateFlow(AddBikeState())
    val state = _state.asStateFlow()


    fun onEvent(event: AddBikeEvent) {
        when (event) {
            is AddBikeEvent.Submit -> {
                if (bikeNameIsValid()) {
                    _state.update { newState ->
                        newState.copy(isValidatedSuccessfully = true, bikeNameError = null)
                    }

                    val bike = Bike(
                        id = bikeId,
                        name = _state.value.bikeName,
                        wheelSize = _state.value.wheelSize,
                        serviceIn = _state.value.serviceIn.toInt(),
                        isDefault = _state.value.isDefault,
                        bikeType = _state.value.bikePagerList[_state.value.selectedBike].type,
                        bikeColor = _state.value.bikePagerList[_state.value.selectedBike].color
                    )
                    viewModelScope.launch {
                        addBike(bike)
                    }

                }


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

            is AddBikeEvent.OnBikeNameAdded -> {
                _state.update { newState ->
                    newState.copy(bikeName = event.bikeName)
                }
            }

            is AddBikeEvent.OnWheelSizeAdded -> {
                _state.update { newState ->
                    newState.copy(wheelSize = event.wheelSize)
                }
            }

            is AddBikeEvent.OnServiceIntervalAdded -> {
                _state.update { newState ->
                    newState.copy(serviceIn = event.serviceInterval)
                }
            }

            is AddBikeEvent.OnDefaultBikeAdded -> {
                _state.update { newState ->
                    newState.copy(isDefault = event.isDefault)
                }
            }
        }
    }

    private fun bikeNameIsValid(): Boolean {
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

    private fun loadBike(bike: Bike) {
        _state.update { newState ->
            val list = _state.value.bikePagerList.toMutableList()
            list[_state.value.selectedBike] =
                list[_state.value.selectedBike].copy(color = bike.bikeColor, type = bike.bikeType)
            newState.copy(
                bikeTitle = bike.bikeType.type,
                bikeName = bike.name,
                wheelSize = bike.wheelSize,
                serviceIn = bike.serviceIn.toString(),
                isDefault = bike.isDefault,
                bikeType = bike.bikeType,
                bikeColor = bike.bikeColor,
                bikePagerList = list
            )
        }
    }
}