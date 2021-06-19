package com.capricorn.advertisement.ui.details

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capricorn.advertisement.data.dto.Advertisement
import com.capricorn.advertisement.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Muhammad Umar on 16/06/2021.
 */

class DetailsViewModel @Inject constructor():BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val advertisementPrivate = MutableLiveData<Advertisement>()
    val advertisementData: LiveData<Advertisement> get() = advertisementPrivate

    fun initFromIntentData(advertisement: Advertisement){
        advertisementPrivate.value = advertisement
    }

}