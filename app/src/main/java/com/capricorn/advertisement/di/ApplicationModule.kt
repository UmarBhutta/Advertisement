package com.capricorn.advertisement.di

import android.content.Context
import com.capricorn.advertisement.data.local.LocalData
import com.capricorn.advertisement.utils.DateTimeUtility
import com.capricorn.advertisement.utils.NetworkConnectivity
import com.capricorn.advertisement.utils.NetworkUtils
import com.capricorn.advertisement.utils.TimeUtils
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * Created by Muhammad Umar on 11/06/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideLocalData(@ApplicationContext context: Context,moshi: Moshi):LocalData{
        return LocalData(context,moshi)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return NetworkUtils(context)
    }

    @Provides
    @Singleton
    fun provideDateTimeUtility():DateTimeUtility{
        return TimeUtils()
    }

}