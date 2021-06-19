package com.capricorn.advertisement.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.capricorn.advertisement.LAST_UPDATE_TIMESTAMP
import com.capricorn.advertisement.POKEMON_LIST_KEY
import com.capricorn.advertisement.SHARED_PREFERENCES_FILE_NAME
import com.capricorn.advertisement.data.Result
import com.capricorn.advertisement.data.dto.AdvertisementList
import com.capricorn.advertisement.data.error.NO_INTERNET_CONNECTION
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.joda.time.LocalDateTime
import javax.inject.Inject

/**
 * Created by Muhammad Umar on 15/06/2021.
 */
class LocalData @Inject constructor(private val context: Context,private val moshi: Moshi){

    fun cachePokemonList(advertisementList: AdvertisementList?): Result<Boolean> {
        //get shared preference
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME,MODE_PRIVATE)

        //convert data to Json
        val type = Types.newParameterizedType(AdvertisementList::class.java)
        val adapter = moshi.adapter<AdvertisementList>(type)
        val convertedString = adapter.toJson(advertisementList)

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(POKEMON_LIST_KEY,convertedString)
        editor.putString(LAST_UPDATE_TIMESTAMP,getCurrentTimeStamp())
        editor.apply()

        val isSuccess = editor.commit()
        return Result.Success(isSuccess)
    }

    fun getListFromLocalCache(): Result<AdvertisementList> {
        //get shared preference
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME,MODE_PRIVATE)

        //convert Json to data
        val type = Types.newParameterizedType(AdvertisementList::class.java)
        val adapter = moshi.adapter<AdvertisementList>(type)
        val pokemonListString = sharedPreferences.getString(POKEMON_LIST_KEY,"")
        //return Network Error in case there is no data, which means data was not updated and api was failed due to some issues
        return if(pokemonListString?.isEmpty()!!){

            Result.Error(NO_INTERNET_CONNECTION)
        }else{
            Result.Success(adapter.fromJson(pokemonListString))
        }
    }

    fun getLastUpdateTimeStamp():String?{
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME,MODE_PRIVATE)
        return sharedPreferences.getString(LAST_UPDATE_TIMESTAMP,"")
    }

    fun getCurrentTimeStamp():String{
        return LocalDateTime.now().toString()
    }


}