package com.capricorn.advertisement.util

import com.capricorn.advertisement.data.dto.Advertisement
import com.capricorn.advertisement.data.dto.AdvertisementList
import com.capricorn.advertisement.data.dto.Pagination
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File

/**
 * Created by Muhammad Umar on 13/06/2021.
 */
class TestAdvertisementModelsGenerator {

    private var advertisementList:AdvertisementList = AdvertisementList(emptyList(),Pagination(null))

    init {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(AdvertisementList::class.java)
        val adapter = moshi.adapter<AdvertisementList>(type)
        val jsonString = getJson("advertisementList.json")
        advertisementList = adapter.fromJson(jsonString)!!

        print("advertisement list $advertisementList")
    }

    fun generateAdvertisementList():AdvertisementList?{
        return advertisementList
    }


    fun generateAdvertisementListWithEmptyList(): AdvertisementList {
        return AdvertisementList(emptyList(), Pagination(null))
    }

    fun generateAdvertisementItemModel(): Advertisement {
        return advertisementList.advertisements[0]
    }

    fun getStubTitle(): String {
        return advertisementList.advertisements[0].name
    }




    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}