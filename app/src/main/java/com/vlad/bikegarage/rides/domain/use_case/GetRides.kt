package com.vlad.bikegarage.rides.domain.use_case

import com.vlad.bikegarage.rides.domain.RideRepository
import com.vlad.bikegarage.rides.domain.model.Ride
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRides @Inject constructor(private val rideRepository: RideRepository) {

    operator fun invoke(): Flow<List<Ride>> {
        return rideRepository.getAllRides()
    }
}