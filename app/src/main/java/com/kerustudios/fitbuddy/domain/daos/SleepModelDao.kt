package com.kerustudios.fitbuddy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kerustudios.fitbuddy.data.entities.SleepModel

@Dao
interface SleepModelDao {
    @Query("SELECT * FROM WaterModel WHERE date = :date")
    fun loadAllByDate(date: String): List<SleepModel>

    @Insert
    fun insertAll(sleepModel: SleepModel)

}