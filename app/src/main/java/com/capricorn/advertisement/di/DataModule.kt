package com.capricorn.advertisement.di

import com.capricorn.advertisement.data.DataRepositoryImpl
import com.capricorn.advertisement.data.DataRepository
import com.capricorn.advertisement.data.api.NetworkDataImpl
import com.capricorn.advertisement.data.api.NetworkData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Muhammad Umar on 11/06/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideNetworkData(networkData: NetworkDataImpl) : NetworkData


    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepositoryImpl) : DataRepository

}