package com.capricorn.advertisement.di


import com.capricorn.advertisement.data.error.mapper.ErrorMapper
import com.capricorn.advertisement.data.error.mapper.Mapper
import com.capricorn.advertisement.errorFactory.ErrorFactory
import com.capricorn.advertisement.errorFactory.ErrorFactoryImpl
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
abstract class ErrorModule {

    @Binds
    @Singleton
    abstract fun provideErrorFactoryManager(errorFactoryImpl: ErrorFactoryImpl) : ErrorFactory

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper) : Mapper

}