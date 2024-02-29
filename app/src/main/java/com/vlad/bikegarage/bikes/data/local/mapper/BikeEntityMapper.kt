package com.vlad.bikegarage.bikes.data.local.mapper

import com.vlad.bikegarage.bikes.data.local.entity.BikeEntity
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.model.BikeType

fun BikeEntity.toBike(): Bike {
    return Bike(
        id = bikeId,
        name = name,
        wheelSize = wheelSize,
        serviceIn = serviceIn,
        isDefault = isDefault,
        bikeType = BikeType.fromString(bikeType),
        bikeColor = bikeColor
    )
}