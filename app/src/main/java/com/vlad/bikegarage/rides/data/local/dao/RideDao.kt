package com.vlad.bikegarage.rides.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vlad.bikegarage.rides.data.local.entity.RideEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RideDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRide(ride: RideEntity)

    @Delete
    suspend fun deleteRide(ride: RideEntity)

    @Query("select * from rides_table")
    fun getAllRides(): Flow<List<RideEntity>>
}