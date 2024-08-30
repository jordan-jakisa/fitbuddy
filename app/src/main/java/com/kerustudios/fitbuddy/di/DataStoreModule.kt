package com.kerustudios.fitbuddy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(ActivityComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}