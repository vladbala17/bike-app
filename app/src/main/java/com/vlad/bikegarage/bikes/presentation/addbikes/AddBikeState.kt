package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.compose.ui.graphics.toArgb
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.ui.theme.White
import com.vlad.bikegarage.util.UiText

data class AddBikeState(
    val bikeType: BikeType = BikeType.Electric,
    val bikeName: String = "",
    val bikeTitle: String = BikeType.Electric.type,
    val bikeNameError: UiText? = null,
    val bikeColor: Int = White.toArgb(),
    val wheelSize: String = "",
    val serviceIn: String = "1000",
    val defaultUnit: String = "KM",
    val isDefault: Boolean = false
)