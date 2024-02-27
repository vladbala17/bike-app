package com.vlad.bikegarage.bikes.domain.model

import androidx.annotation.DrawableRes

data class PagerBike(
    val title: String,
    val type: BikeType,
    @DrawableRes val wheels: Int,
    @DrawableRes val middle: Int,
    @DrawableRes val over: Int,
    val color: Int = 0
)
