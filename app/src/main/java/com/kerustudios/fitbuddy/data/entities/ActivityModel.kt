package com.kerustudios.fitbuddy.data.entities

import androidx.compose.ui.graphics.vector.ImageVector

data class ActivityModel(
    val id: Long = System.currentTimeMillis(),
    val icon: ImageVector? = null,
    val reps: Int = Int.MIN_VALUE,
    val name: String = "",
)
