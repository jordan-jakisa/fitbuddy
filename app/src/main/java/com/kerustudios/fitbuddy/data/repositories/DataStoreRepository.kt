package com.kerustudios.fitbuddy.data.repositories

import androidx.datastore.preferences.core.Preferences

interface DataStoreRepository {
    suspend fun <T> readValue(key: Preferences.Key<T>): T?
    suspend fun <T> setValue(key: Preferences.Key<T>, value: T)
}