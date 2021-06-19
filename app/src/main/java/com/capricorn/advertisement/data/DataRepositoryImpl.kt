package com.capricorn.advertisement.data


import com.capricorn.advertisement.data.api.NetworkDataImpl
import com.capricorn.advertisement.data.dto.AdvertisementList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Muhammad Umar on 11/06/2021.
 */
class DataRepositoryImpl @Inject constructor(private val networkDataImpl: NetworkDataImpl,
                                             private val ioDispatcher:CoroutineContext): DataRepository {

    override suspend fun requestAdvertisementList(): Flow<Result<AdvertisementList>> {
       return flow {
           emit(networkDataImpl.requestAdvertisementList())
       }.flowOn(ioDispatcher)
    }

}