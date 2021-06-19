package com.capricorn.advertisement.data.api.service

import com.capricorn.advertisement.data.dto.AdvertisementList
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Muhammad Umar on 11/06/2021.
 */
interface ApiService {

    @GET("/default/dynamodb-writer")
    suspend fun getPokemonList() : Response<AdvertisementList>

}