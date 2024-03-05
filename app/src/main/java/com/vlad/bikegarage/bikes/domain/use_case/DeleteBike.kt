package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.bikes.domain.BikeRepository
import javax.inject.Inject

class DeleteBike @Inject constructor(private val bikeRepository: BikeRepository) {
    suspend operator fun invoke(bikeName: String) {
        bikeRepository.deleteBike(bikeName)
    }
}