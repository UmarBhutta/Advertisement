package com.capricorn.advertisement.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Muhammad Umar on 15/06/2021.
 */
@JsonClass(generateAdapter = false)
data class AdvertisementList(
    @Json(name = "results") val results : List<Advertisement>,
    @Json(name = "pagination") val pagination: Pagination
    )