package com.kerustudios.fitbuddy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kerustudios.fitbuddy.data.entities.ActivityModel
import com.kerustudios.fitbuddy.data.entities.SleepModel
import com.kerustudios.fitbuddy.data.entities.WaterModel
import com.kerustudios.fitbuddy.domain.daos.ActivityModelDao
import com.kerustudios.fitbuddy.domain.daos.SleepModelDao
import com.kerustudios.fitbuddy.domain.daos.WaterModelDao

@Database(entities = [SleepModel::class, WaterModel::class, ActivityModel::class], version = 3)
abstract class Database : RoomDatabase() {
    abstract fun waterDao(): WaterModelDao
    abstract fun sleepDao(): SleepModelDao
    abstract fun activityDao(): ActivityModelDao
}