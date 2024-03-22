package com.vlad.bikegarage.bikes.data.repo

import com.vlad.bikegarage.bikes.data.local.dao.BikeDao
import com.vlad.bikegarage.bikes.data.local.mapper.toBike
import com.vlad.bikegarage.bikes.data.local.mapper.toBikeEntity
import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.rides.data.local.mapper.toRide
import com.vlad.bikegarage.rides.domain.model.Ride
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BikesRepositoryImpl @Inject constructor(private val bikeDao: BikeDao) : BikeRepository {
    override suspend fun addBike(bike: Bike) {
        bikeDao.insertBike(bike.toBikeEntity())
    }

    override suspend fun getBikeById(bikeId: Int): Bike {
        return bikeDao.getBikeById(bikeId).toBike()
    }

    override suspend fun deleteBike(bikeName: String) {
        bikeDao.deleteBike(bikeName)
    }

    override suspend fun getAllRidesForBike(bikeName: String): List<Ride> {
        return bikeDao.getAllRidesForBike(bikeName).map { entities -> entities.toRide() }
    }

    override suspend fun getBikeByName(bikeName: String): Bike {
        return bikeDao.getBikeByName(bikeName).toBike()
    }

    override fun getBikes(): Flow<List<Bike>> {
        return bikeDao.getBikes().map { entities ->
            entities.map { bikeEntity -> bikeEntity.toBike() }
        }
    }
}