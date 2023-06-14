package com.vlad.bikegarage.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivityViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()


    fun onEvent(event: MainScreenEvent) {
        when (event){
            is MainScreenEvent.PageChanged -> {
                _state.update {
                    it.copy(title = event.destination)
                }
            }

        }
    }
}