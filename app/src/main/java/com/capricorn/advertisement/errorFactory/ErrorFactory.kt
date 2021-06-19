package com.capricorn.advertisement.errorFactory

import com.capricorn.advertisement.data.error.Error


/**
 * Created by Muhammad Umar on 11/06/2021.
 */
interface ErrorFactory {
    fun getError(errorCode:Int): Error
}