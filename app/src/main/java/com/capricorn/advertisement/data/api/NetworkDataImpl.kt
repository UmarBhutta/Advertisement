package com.capricorn.advertisement.data.api


import com.capricorn.advertisement.data.Result
import com.capricorn.advertisement.data.api.service.ApiService
import com.capricorn.advertisement.data.dto.AdvertisementList
import com.capricorn.advertisement.data.error.NETWORK_ERROR
import com.capricorn.advertisement.data.error.NO_INTERNET_CONNECTION
import com.capricorn.advertisement.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Muhammad Umar on 11/06/2021.
 */
class NetworkDataImpl @Inject constructor(private val apiService: ApiService, private val networkConnectivity: NetworkConnectivity):
    NetworkData {
    override suspend fun requestAdvertisementList(): Result<AdvertisementList> {
        return when (val response = processCall(apiService::getPokemonList)) {
            is AdvertisementList -> {
                Result.Success(data = response)
            }
            else -> {
               Result.Error(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}