package com.kerustudios.fitbuddy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kerustudios.fitbuddy.data.entities.ActivityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityModelDao {
    @Query("SELECT * FROM ActivityModel WHERE date = :date")
    fun loadAllByDate(date: String): Flow<List<ActivityModel>>

    @Insert
    suspend fun insertAll(activityModel: ActivityModel)
}