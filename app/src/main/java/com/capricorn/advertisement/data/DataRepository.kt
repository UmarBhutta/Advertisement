package com.capricorn.advertisement.data

import com.capricorn.advertisement.data.dto.AdvertisementList
import kotlinx.coroutines.flow.Flow


/**
 * Created by Muhammad Umar on 11/06/2021.
 */
interface DataRepository {
    suspend fun requestAdvertisementList():Flow<Result<AdvertisementList>>
}