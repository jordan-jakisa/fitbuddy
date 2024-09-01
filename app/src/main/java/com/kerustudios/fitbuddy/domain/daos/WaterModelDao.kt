package com.kerustudios.fitbuddy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kerustudios.fitbuddy.data.entities.WaterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterModelDao {

    @Query("SELECT * FROM WaterModel WHERE date = :date")
    fun loadAllByDate(date: String): Flow<List<WaterModel>>

    @Insert
    suspend fun insertAll(waterModels: WaterModel)

}