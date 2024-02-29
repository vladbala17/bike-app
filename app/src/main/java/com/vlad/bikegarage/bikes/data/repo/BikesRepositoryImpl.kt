package com.vlad.bikegarage.bikes.data.repo

import com.vlad.bikegarage.bikes.data.local.dao.BikeDao
import com.vlad.bikegarage.bikes.data.local.mapper.toBike
import com.vlad.bikegarage.bikes.data.local.mapper.toBikeEntity
import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.bikes.domain.model.Bike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BikesRepositoryImpl @Inject constructor(private val bikeDao: BikeDao) : BikeRepository {
    override suspend fun addBike(bike: Bike) {
        bikeDao.insertBike(bike.toBikeEntity())
    }

    override fun getBikes(): Flow<List<Bike>> {
        return bikeDao.getBikes().map { entities ->
            entities.map { bikeEntity -> bikeEntity.toBike() }
        }
    }
}