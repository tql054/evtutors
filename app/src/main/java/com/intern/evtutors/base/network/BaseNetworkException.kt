package com.intern.evtutors.base.network

class BaseNetworkException (
    val responseMessage: String? = null,
    val responseCode: Int = -1
) : Exception() {

    var mainMessage = ""

    fun parseFromString(errorBody: String) {

    }
}