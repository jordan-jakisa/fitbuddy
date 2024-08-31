package com.kerustudios.fitbuddy.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
* @param date The date of the water entry
* Should be in the format "dd-MM-yyyy"
* */
@Entity
data class WaterModel(
    @PrimaryKey val id: Int,
    val date: String,
    val amount: Float,
    val unit: String
)
