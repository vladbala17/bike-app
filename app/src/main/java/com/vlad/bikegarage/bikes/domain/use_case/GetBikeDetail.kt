package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.bikes.domain.model.Bike
import javax.inject.Inject

class GetBikeDetail @Inject constructor(private val bikeRepository: BikeRepository) {
    suspend operator fun invoke(bikeId: Int): Bike {
        return bikeRepository.getBikeById(bikeId)
    }
}