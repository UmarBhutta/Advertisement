package com.capricorn.advertisement

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.capricorn.advertisement.data.dto.AdvertisementList
import com.capricorn.advertisement.data.dto.Pagination
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.InputStream

object TestDataUtil {

    var dataSuccess: LoadStatus = LoadStatus.Success
    var advertisementList:AdvertisementList = AdvertisementList(emptyList(), Pagination(null))

    fun initData():AdvertisementList{
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(AdvertisementList::class.java)
        val adapter = moshi.adapter<AdvertisementList>(type)
        val jsonString = getJson("advertisementList.json")
        advertisementList = adapter.fromJson(jsonString)!!
        return advertisementList
    }


    private fun getJson(path:String):String{
        val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val inputStream: InputStream = ctx.classLoader.getResourceAsStream(path)
        return inputStream.bufferedReader().use { it.readText() }
    }
}