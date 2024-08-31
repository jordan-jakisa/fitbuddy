package com.kerustudios.fitbuddy.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SleepModel(
    @PrimaryKey val id: Int,
    val date: String,
    val amount: Float,
    val unit: String
)
