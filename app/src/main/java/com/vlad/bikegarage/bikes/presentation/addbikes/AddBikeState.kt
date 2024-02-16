package com.vlad.bikegarage.bikes.presentation.addbikes

import com.vlad.bikegarage.util.UiText

data class AddBikeState(
    val bikeName: String = "",
    val bikeNameError: UiText? = null
)