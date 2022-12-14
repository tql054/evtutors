package com.intern.evtutors.base.network

import com.intern.evtutors.BuildConfig


object NetworkHelper {

    fun getDefaultHeader(): Map<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = "Bearer ${BuildConfig.BASE_URL_AGORA_VIDEO_CALL_DEV}"
        return headers.toMap()
    }

//    fun getDefaultHeaderForLesson(): Map<String, String> {
//        val headers = mutableMapOf<String, String>()
//        headers["Content-Type"] = "application/json"
//        headers["Authorization"] = "Bearer ${DataLocal.CUSTOMER_TOKEN}"
//        return headers.toMap()
//    }

}