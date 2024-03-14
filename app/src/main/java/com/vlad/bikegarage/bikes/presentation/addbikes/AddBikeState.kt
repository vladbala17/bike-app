package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.compose.ui.graphics.toArgb
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.bikes.domain.model.PagerBike
import com.vlad.bikegarage.ui.theme.Green
import com.vlad.bikegarage.ui.theme.Pink
import com.vlad.bikegarage.ui.theme.Red
import com.vlad.bikegarage.ui.theme.White
import com.vlad.bikegarage.ui.theme.Yellow
import com.vlad.bikegarage.util.UiText

data class AddBikeState(
    val bikeType: BikeType = BikeType.Electric,
    val bikeName: String = "",
    val bikeTitle: String = BikeType.Electric.type,
    val bikeNameError: UiText? = null,
    val bikeColor: Int = White.toArgb(),
    val wheelSize: String = "29'",
    val serviceIn: Int = 1000,
    val defaultUnit: String = "KM",
    val isDefault: Boolean = false,
    val bikePagerList: List<PagerBike> = buildPagerBikeList(),
    val selectedBike: Int = 0,
    val isValidatedSuccessfully: Boolean = false
)


fun buildPagerBikeList() = listOf(
    PagerBike(
        title = BikeType.RoadBike.type,
        type = BikeType.RoadBike,
        wheels = R.drawable.bike_roadbike_big_wheels,
        middle = R.drawable.bike_roadbike_middle,
        over = R.drawable.bike_roadbike_over,
        color = Yellow.toArgb()
    ),
    PagerBike(
        title = BikeType.MTB.type,
        type = BikeType.MTB,
        wheels = R.drawable.bike_mtb_big_wheels,
        middle = R.drawable.bike_mtb_middle,
        over = R.drawable.bike_mtb_over,
        color = Green.toArgb()
    ),
    PagerBike(
        title = BikeType.Electric.type,
        type = BikeType.Electric,
        wheels = R.drawable.bike_electric_big_wheels,
        middle = R.drawable.bike_electric_middle,
        over = R.drawable.bike_electric_over,
        color = Red.toArgb()
    ),
    PagerBike(
        title = BikeType.Hybrid.type,
        type = BikeType.Hybrid,
        wheels = R.drawable.bike_hybrid_big_wheels,
        middle = R.drawable.bike_hybrid_middle,
        over = R.drawable.bike_hybrid_over,
        color = Pink.toArgb()
    )
)

