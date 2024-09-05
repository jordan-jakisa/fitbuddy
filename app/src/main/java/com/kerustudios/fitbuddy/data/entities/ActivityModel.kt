package com.kerustudios.fitbuddy.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kerustudios.fitbuddy.utils.getCurrentDate


@Entity
data class ActivityModel(
    @PrimaryKey val id: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val date: String = getCurrentDate(),
    val icon: String = "",
    val reps: Int = Int.MIN_VALUE,
    val name: String? = null,
    val units: String = ""
)