package com.vlad.bikegarage.bikes.domain

import com.vlad.bikegarage.bikes.domain.model.Bike

interface BikeRepository {

    suspend fun addBike(bike: Bike)
}