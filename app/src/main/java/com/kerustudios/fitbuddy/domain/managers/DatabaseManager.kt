package com.kerustudios.fitbuddy.domain.managers

import com.kerustudios.fitbuddy.data.database.Database
import com.kerustudios.fitbuddy.data.entities.SleepModel
import com.kerustudios.fitbuddy.data.entities.WaterModel
import com.kerustudios.fitbuddy.ui.dialogs.Habit
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlin.random.Random

class DatabaseManager @Inject constructor(
    private val database: Database
) {

    suspend fun saveEntry(value: Float, habit: Habit) {
        when (habit) {
            is Habit.WATER -> {
                database.waterDao().insertAll(
                    waterModels = WaterModel(
                        date = SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis()),
                        amount = value,
                        unit = "liters",
                        id = Random.nextInt()
                    )
                )
            }

            is Habit.SLEEP -> {
                database.sleepDao().insertAll(
                    sleepModel = SleepModel(
                        date = SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis()),
                        amount = value,
                        unit = "liters",
                        id = Random.nextInt()
                    )
                )
            }
        }
    }

}