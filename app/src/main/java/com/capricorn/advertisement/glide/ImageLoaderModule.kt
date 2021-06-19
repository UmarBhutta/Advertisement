package com.capricorn.advertisement.glide

import android.content.Context
import android.os.Build
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.capricorn.advertisement.BuildConfig

/**
 * Created by Muhammad Umar on 16/06/2021.
 */

@GlideModule
class ImageLoaderModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        val diskCacheSize = java.lang.Long.valueOf((20 * 1024 * 1024).toLong())
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(Log.VERBOSE)
        }
        val options = RequestOptions()
        val isAndroidO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        if (isAndroidO) options.disallowHardwareConfig()

        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSize))
        builder.setDefaultRequestOptions(options)

    }
}