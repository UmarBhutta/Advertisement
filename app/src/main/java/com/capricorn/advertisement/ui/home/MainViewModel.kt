package com.capricorn.advertisement.ui.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.capricorn.advertisement.data.DataRepository
import com.capricorn.advertisement.data.Result
import com.capricorn.advertisement.data.dto.Advertisement
import com.capricorn.advertisement.data.dto.AdvertisementList
import com.capricorn.advertisement.ui.base.BaseViewModel
import com.capricorn.advertisement.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : BaseViewModel() {

    @VisibleForTesting
    private val advertisementListPrivate = MutableLiveData<Result<AdvertisementList>>()
    val advertisementList: LiveData<Result<AdvertisementList>> get() = advertisementListPrivate

    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    @VisibleForTesting
    private val openAdvertisementDetailsPrivate = MutableLiveData<SingleEvent<Advertisement>>()
    val openAdvertisementDetails: LiveData<SingleEvent<Advertisement>> get() = openAdvertisementDetailsPrivate

    /**
     * Error handling as UI
     */
    @VisibleForTesting
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    /**
     * [getAdvertisementList] get Advertisement List from he data repository
     */
    fun getAdvertisementList(){
        viewModelScope.launch {
            advertisementListPrivate.value = Result.Loading()
            dataRepository.requestAdvertisementList().collect {
                advertisementListPrivate.value = it
            }

        }
    }

    /**
     * [showAdvertisementDetails] open Pokemon Details when click on Advertisement item on recylerview
     * [advertisement] is the object which is clicked
     */
    fun showAdvertisementDetails(advertisement: Advertisement){

        openAdvertisementDetailsPrivate.value = SingleEvent(advertisement)
    }

    /**
     * [showToastMessage] show Error Toast Message
     */
    fun showToastMessage(errorCode: Int) {
        val error = errorFactoryManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}