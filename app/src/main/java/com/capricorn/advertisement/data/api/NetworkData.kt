package com.capricorn.advertisement.data.api

import com.capricorn.advertisement.data.Result
import com.capricorn.advertisement.data.dto.AdvertisementList

/**
 * Created by Muhammad Umar on 11/06/2021.
 */
interface NetworkData {
    suspend fun requestAdvertisementList(): Result<AdvertisementList>
}