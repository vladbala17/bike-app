package com.vlad.bikegarage.bikes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vlad.bikegarage.bikes.data.local.dao.BikeDao
import com.vlad.bikegarage.bikes.data.local.entity.BikeEntity

@Database(entities = [BikeEntity::class], version = 1)
abstract class BikesDatabase : RoomDatabase() {
    abstract val dao: BikeDao
}