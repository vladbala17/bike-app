package com.vlad.bikegarage.main

sealed class MainScreenEvent {
    data class PageChanged(val destination: String): MainScreenEvent()
}
