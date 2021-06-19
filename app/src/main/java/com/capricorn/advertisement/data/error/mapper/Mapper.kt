package com.capricorn.advertisement.data.error.mapper

/**
 * Created by Muhammad Umar on 11/06/2021.
 */
interface Mapper {
    fun getErrorString(stringResourceId: Int): String
    val errorsMap: Map<Int, String>
}