package com.kerustudios.fitbuddy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kerustudios.fitbuddy.data.entities.WaterModel

@Dao
interface WaterModelDao {

    @Query("SELECT * FROM WaterModel WHERE date = :date")
    fun loadAllByDate(date: String): List<WaterModel>

    @Insert
    fun insertAll(waterModels: WaterModel)

}