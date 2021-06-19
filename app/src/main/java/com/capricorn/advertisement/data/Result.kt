package com.capricorn.advertisement.data

/**
 * Created by Muhammad Umar on 11/06/2021.
 * A generic class contains data, and status of process and error details in case of failure
 */
sealed class Result<T> (
    val data:T? = null,
    val errorCode:Int? = null){

    class Success<T>(data: T?) : Result<T>(data)
    class Loading<T>(data: T? = null ) : Result<T>(data)
    class Error<T>(errorCode: Int?) : Result<T>(errorCode = errorCode)


}