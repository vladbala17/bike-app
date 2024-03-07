package com.vlad.bikegarage.rides.domain.use_case

import com.vlad.bikegarage.rides.domain.RideRepository
import com.vlad.bikegarage.rides.domain.model.Ride
import javax.inject.Inject

class AddRide @Inject constructor(private val rideRepository: RideRepository) {

    suspend operator fun invoke(ride: Ride) {
        rideRepository.addRide(ride)
    }
}