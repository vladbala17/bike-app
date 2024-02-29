package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.bikes.domain.model.Bike
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBikes @Inject constructor(private val repository: BikeRepository) {
    operator fun invoke(): Flow<List<Bike>> {
        return repository.getBikes()
    }
}