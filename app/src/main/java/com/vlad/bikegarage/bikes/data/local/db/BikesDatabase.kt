package com.vlad.bikegarage.bikes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vlad.bikegarage.bikes.data.local.dao.BikeDao
import com.vlad.bikegarage.bikes.data.local.entity.BikeEntity
import com.vlad.bikegarage.rides.data.local.dao.RideDao
import com.vlad.bikegarage.rides.data.local.entity.RideEntity

@Database(entities = [BikeEntity::class, RideEntity::class], version = 2)
abstract class BikesDatabase : RoomDatabase() {
    abstract val bikeDao: BikeDao
    abstract val rideDao: RideDao
}