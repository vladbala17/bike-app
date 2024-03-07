package com.vlad.bikegarage.rides.data.local.mapper

import com.vlad.bikegarage.rides.data.local.entity.RideEntity
import com.vlad.bikegarage.rides.domain.model.Ride

fun RideEntity.toRide(): Ride {
    return Ride(
        id = id,
        bikeName = bikeName,
        rideName = rideName,
        distance = distance,
        duration = duration,
        date = date
    )
}