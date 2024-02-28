package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.bikes.domain.model.Bike
import javax.inject.Inject

class AddBike @Inject constructor(
    private val repository: BikeRepository
) {
    suspend operator fun invoke(
        bike: Bike
    ) {
        repository.addBike(bike)
    }
}