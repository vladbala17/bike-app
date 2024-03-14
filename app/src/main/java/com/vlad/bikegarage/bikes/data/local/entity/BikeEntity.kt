package com.vlad.bikegarage.bikes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bikes_table")
data class BikeEntity(
    @PrimaryKey(autoGenerate = true) val bikeId: Int = 0,
    val name: String,
    val wheelSize: String,
    val serviceIn: Int,
    val isDefault: Boolean,
    val bikeType: String,
    val bikeColor: Int
)
