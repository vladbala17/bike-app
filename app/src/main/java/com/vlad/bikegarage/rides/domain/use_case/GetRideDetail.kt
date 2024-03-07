package com.vlad.bikegarage.rides.domain.use_case

import com.vlad.bikegarage.rides.domain.RideRepository
import com.vlad.bikegarage.rides.domain.model.Ride
import javax.inject.Inject

class GetRideDetail @Inject constructor(private val rideRepository: RideRepository) {
    suspend operator fun invoke(rideId: Int): Ride {
        return rideRepository.getRideById(rideId)
    }
}