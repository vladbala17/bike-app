package com.vlad.bikegarage.bikes.domain

import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.rides.domain.model.Ride
import kotlinx.coroutines.flow.Flow

interface BikeRepository {

    suspend fun addBike(bike: Bike)

    suspend fun getBikeById(bikeId: Int): Bike

    suspend fun deleteBike(bikeName: String)

    suspend fun getAllRidesForBike(bikeName: String): List<Ride>

    suspend fun getBikeByName(bikeName: String): Bike

    fun getBikes(): Flow<List<Bike>>
}