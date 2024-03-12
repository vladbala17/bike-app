package com.vlad.bikegarage.rides.domain.use_case

import com.vlad.bikegarage.rides.domain.RideRepository
import javax.inject.Inject

class DeleteRide @Inject constructor(private val rideRepository: RideRepository) {
    suspend operator fun invoke(rideName: String) {
        rideRepository.deleteRide(rideName)
    }
}