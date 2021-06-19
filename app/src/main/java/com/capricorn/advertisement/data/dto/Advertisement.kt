package com.capricorn.advertisement.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by Muhammad Umar on 11/06/2021.
 */
@JsonClass(generateAdapter = false)
@Parcelize
data class Advertisement(
    @field:Json(name = "created_at") var createdAt : String? = "",
    @field:Json(name = "price") var price : String? = "",
    @field:Json(name = "name") var name : String? = "",
    @field:Json(name = "uid") var uid : String? = "",
    @field:Json(name = "image_ids") var imageIds : List<String>,
    @field:Json(name = "image_urls") var imageUrls : List<String>,
    @field:Json(name = "image_urls_thumbnails") var imageUrlsThumbnails : List<String>
):Parcelable
