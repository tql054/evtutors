package com.intern.evtutors.base.network

sealed class NetworkResult2<out T: Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult2<T>()
    data class Error(val msg:String ) : NetworkResult2<Nothing>()
    object Loading : NetworkResult2<Nothing>()
}
