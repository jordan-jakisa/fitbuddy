package com.kerustudios.fitbuddy.domain.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.kerustudios.fitbuddy.data.repositories.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    override suspend fun <T> readValue(key: Preferences.Key<T>): T? {
        return dataStore.data.map {
            it[key]
        }.first()
    }

    override suspend fun <T> setValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit {
            it[key] = value
        }
    }
}