package com.capricorn.advertisement

import com.capricorn.advertisement.LoadStatus.*
import com.capricorn.advertisement.TestDataUtil.dataSuccess
import com.capricorn.advertisement.TestDataUtil.initData
import com.capricorn.advertisement.data.DataRepository
import com.capricorn.advertisement.data.Result
import com.capricorn.advertisement.data.dto.AdvertisementList
import com.capricorn.advertisement.data.dto.Pagination
import com.capricorn.advertisement.data.error.NETWORK_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class TestDataRepository @Inject constructor() : DataRepository {

    override suspend fun requestAdvertisementList(): Flow<Result<AdvertisementList>> {
        return when(dataSuccess){
            Success ->{
                flow { emit(Result.Success(initData()))  }
            }

            Fail -> {
                flow { emit(Result.Error<AdvertisementList>(errorCode = NETWORK_ERROR)) }
            }

            EmptyResponse -> {
                flow { emit(Result.Success(AdvertisementList(emptyList(), Pagination()))) } }


        }
    }
}