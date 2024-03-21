package com.vlad.bikegarage.rides.domain.model

import com.vlad.bikegarage.bikes.domain.model.BikeType

data class RideChartRow(val type: BikeType, val km: Int)
