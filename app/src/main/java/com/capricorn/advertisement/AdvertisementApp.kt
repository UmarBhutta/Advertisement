package com.capricorn.advertisement

import android.app.Application
import androidx.startup.AppInitializer
import dagger.hilt.android.HiltAndroidApp
import net.danlew.android.joda.JodaTimeInitializer

/**
 * Created by Muhammad Umar on 19/06/2021.
 */

@HiltAndroidApp
open class AdvertisementApp: Application() {
    override fun onCreate() {
        super.onCreate()
        //joda time
        AppInitializer.getInstance(this).initializeComponent(JodaTimeInitializer::class.java)
    }
}