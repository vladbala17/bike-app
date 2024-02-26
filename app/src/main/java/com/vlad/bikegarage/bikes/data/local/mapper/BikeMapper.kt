package com.vlad.bikegarage.bikes.data.local.mapper

import com.vlad.bikegarage.bikes.data.local.entity.BikeEntity
import com.vlad.bikegarage.bikes.domain.model.Bike

fun Bike.toBikeEntity(): BikeEntity {
    return BikeEntity(
        name = name,
        wheelSize = wheelSize,
        serviceIn = serviceIn,
        bikeType = bikeType.type,
        isDefault = isDefault,
        bikeColor = bikeColor
    )
}