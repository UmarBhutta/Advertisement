package com.capricorn.advertisement


sealed class LoadStatus {
    object Success : LoadStatus()
    object Fail : LoadStatus()
    object EmptyResponse : LoadStatus()
}

