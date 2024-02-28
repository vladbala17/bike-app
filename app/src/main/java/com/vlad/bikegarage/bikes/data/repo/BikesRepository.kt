package com.vlad.bikegarage.bikes.data.repo

import com.vlad.bikegarage.bikes.data.local.dao.BikeDao
import com.vlad.bikegarage.bikes.data.local.mapper.toBikeEntity
import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.bikes.domain.model.Bike
import javax.inject.Inject

class BikesRepository @Inject constructor(private val bikeDao: BikeDao) : BikeRepository {
    override suspend fun addBike(bike: Bike) {
        bikeDao.insertBike(bike.toBikeEntity())
    }
}