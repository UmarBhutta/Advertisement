package com.capricorn.advertisement.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Muhammad Umar on 18/06/2021.
 */
@JsonClass(generateAdapter = false)
data class Pagination(
    @Json(name ="key") val key : String? = null
)