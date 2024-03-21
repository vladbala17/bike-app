package com.vlad.bikegarage.rides.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rides_table")
data class RideEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val rideName: String,
    val bikeName: String,
    val bikeType: String,
    val distance: Int,
    val durationHours: Int,
    val durationMinutes: Int,
    val date: Long
)