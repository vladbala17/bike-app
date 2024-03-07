package com.vlad.bikegarage.rides.data.local.mapper

import com.vlad.bikegarage.rides.data.local.entity.RideEntity
import com.vlad.bikegarage.rides.domain.model.Ride

fun Ride.toRideEntity(): RideEntity {
    return RideEntity(
        id = id,
        rideName = rideName,
        bikeName = bikeName,
        distance = distance,
        duration = duration,
        date = date
    )
}