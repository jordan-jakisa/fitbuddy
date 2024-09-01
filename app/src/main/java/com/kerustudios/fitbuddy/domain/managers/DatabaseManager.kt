package com.kerustudios.fitbuddy.domain.managers

import com.kerustudios.fitbuddy.data.database.Database
import com.kerustudios.fitbuddy.data.entities.SleepModel
import com.kerustudios.fitbuddy.data.entities.WaterModel
import com.kerustudios.fitbuddy.ui.dialogs.Habit
import com.kerustudios.fitbuddy.utils.getCurrentDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
                        date = getCurrentDate(
                        ),
                        amount = value,
                        unit = "liters",
                        id = Random.nextInt()
                    )
                )
            }

            is Habit.SLEEP -> {
                database.sleepDao().insertAll(
                    sleepModel = SleepModel(
                        date = getCurrentDate(),
                        amount = value,
                        unit = "hours",
                        id = Random.nextInt()
                    )
                )
            }
        }
    }

    fun getCurrentWaterValues(date: String): Flow<Float> {
        return database.waterDao().loadAllByDate(date)
            .map { list -> list.sumOf { it.amount.toDouble() }.toFloat() }


    }

    fun getCurrentSleepValues(date: String): Flow<Float> {
        return database.sleepDao().loadAllByDate(date)
            .map { list -> list.sumOf { it.amount.toDouble() }.toFloat() }
    }
}