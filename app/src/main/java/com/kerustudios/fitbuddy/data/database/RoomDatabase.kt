package com.kerustudios.fitbuddy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kerustudios.fitbuddy.data.entities.SleepModel
import com.kerustudios.fitbuddy.data.entities.WaterModel
import com.kerustudios.fitbuddy.domain.daos.SleepModelDao
import com.kerustudios.fitbuddy.domain.daos.WaterModelDao

@Database(entities = [SleepModel::class, WaterModel::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun waterDao(): WaterModelDao
    abstract fun sleepDao(): SleepModelDao
}