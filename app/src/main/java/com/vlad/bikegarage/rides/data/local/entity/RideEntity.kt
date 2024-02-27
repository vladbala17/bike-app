package com.vlad.bikegarage.rides.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rides_table")
data class RideEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val bikeName: String,
    val distance: String,
    val duration: Long
)