package com.vlad.bikegarage.rides.data.repository

import com.vlad.bikegarage.rides.data.local.dao.RideDao
import com.vlad.bikegarage.rides.data.local.mapper.toRide
import com.vlad.bikegarage.rides.data.local.mapper.toRideEntity
import com.vlad.bikegarage.rides.domain.RideRepository
import com.vlad.bikegarage.rides.domain.model.Ride
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RidesRepositoryImpl @Inject constructor(private val rideDao: RideDao) : RideRepository {
    override fun getAllRides(): Flow<List<Ride>> {
        return rideDao.getAllRides().map { entities -> entities.map { entity -> entity.toRide() } }
    }

    override suspend fun getRideById(rideId: Int): Ride {
        return rideDao.getRideById(rideId).toRide()
    }

    override suspend fun deleteRide(rideName: String) {
        rideDao.deleteRide(rideName)
    }

    override suspend fun addRide(ride: Ride) {
        rideDao.insertRide(ride.toRideEntity())
    }
}