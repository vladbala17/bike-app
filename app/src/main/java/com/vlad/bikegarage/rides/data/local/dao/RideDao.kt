package com.vlad.bikegarage.rides.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.vlad.bikegarage.rides.data.local.entity.RideEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RideDao {
    @Upsert()
    suspend fun insertRide(ride: RideEntity)

    @Query("delete from rides_table where rideName = :rideName ")
    suspend fun deleteRide(rideName: String)

    @Query("select * from rides_table where id = :id")
    suspend fun getRideById(id: Int): RideEntity

    @Query("select * from rides_table")
    fun getAllRides(): Flow<List<RideEntity>>
}