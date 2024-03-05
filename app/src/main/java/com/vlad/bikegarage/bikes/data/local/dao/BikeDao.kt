package com.vlad.bikegarage.bikes.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.vlad.bikegarage.bikes.data.local.entity.BikeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BikeDao {
    @Upsert()
    suspend fun insertBike(bikeEntity: BikeEntity)

    @Delete
    suspend fun deleteBike(bikeEntity: BikeEntity)
    @Query("select * from bikes_table where bikeId = :bikeId")
    suspend fun getBikeById(bikeId: Int): BikeEntity

    @Query("select * from bikes_table")
    fun getBikes(): Flow<List<BikeEntity>>
}