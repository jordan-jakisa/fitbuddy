package com.kerustudios.fitbuddy.di

import com.kerustudios.fitbuddy.data.repositories.DataStoreRepository
import com.kerustudios.fitbuddy.domain.repositories.DataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DI {

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository

}