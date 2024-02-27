package com.vlad.bikegarage.rides.data.local.entity

import androidx.room.Entity

@Entity
data class RideStatistic(val bikeName: String, val distance: Int)
