package com.vlad.bikegarage.rides.domain

import com.vlad.bikegarage.rides.domain.model.Ride
import kotlinx.coroutines.flow.Flow

interface RideRepository {
    fun getAllRides(): Flow<List<Ride>>

    suspend fun getRideById(rideId: Int): Ride

    suspend fun deleteRide(rideName: String)

    suspend fun addRide(ride: Ride)
}