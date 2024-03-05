package com.vlad.bikegarage.bikes.domain

import com.vlad.bikegarage.bikes.domain.model.Bike
import kotlinx.coroutines.flow.Flow

interface BikeRepository {

    suspend fun addBike(bike: Bike)

    suspend fun getBikeById(bikeId: Int): Bike

    fun getBikes(): Flow<List<Bike>>
}