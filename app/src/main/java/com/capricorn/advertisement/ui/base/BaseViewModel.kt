package com.capricorn.advertisement.ui.base

import androidx.lifecycle.ViewModel
import com.capricorn.advertisement.errorFactory.ErrorFactory
import javax.inject.Inject

/**
 * Created by Muhammad Umar on 09/06/2021.
 */
open class BaseViewModel :ViewModel(){

    @Inject
    lateinit var errorFactoryManager: ErrorFactory
}