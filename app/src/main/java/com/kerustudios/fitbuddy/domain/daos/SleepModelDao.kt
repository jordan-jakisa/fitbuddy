package com.kerustudios.fitbuddy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kerustudios.fitbuddy.data.entities.SleepModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepModelDao {
    @Query("SELECT * FROM SleepModel WHERE date = :date")
    fun loadAllByDate(date: String): Flow<List<SleepModel>>

    @Insert
    suspend fun insertAll(sleepModel: SleepModel)

}