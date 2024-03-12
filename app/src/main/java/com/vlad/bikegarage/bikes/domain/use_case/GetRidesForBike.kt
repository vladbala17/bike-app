package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.rides.domain.model.Ride
import javax.inject.Inject

class GetRidesForBike @Inject constructor(private val bikeRepository: BikeRepository) {
    suspend operator fun invoke(bikeName: String): List<Ride> {
        return bikeRepository.getAllRidesForBike(bikeName)
    }
}