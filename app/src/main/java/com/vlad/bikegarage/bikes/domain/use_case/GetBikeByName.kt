package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.bikes.domain.model.Bike
import javax.inject.Inject

class GetBikeByName @Inject constructor(private val bikeRepository: BikeRepository) {

    suspend operator fun invoke(bikeName: String): Bike {
        return bikeRepository.getBikeByName(bikeName)
    }
}