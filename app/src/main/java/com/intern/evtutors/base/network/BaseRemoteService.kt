package com.intern.evtutors.base.network

import android.util.Log
import retrofit2.Response

open class BaseRemoteService : BaseService() {

    protected suspend fun <T : Any> callApi(call: suspend () -> Response<T>): NetworkResult<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            return NetworkResult.Error("data")
        }
        return if (response.isSuccessful) {
            if (response.body() == null) {
                NetworkResult.Error("")
            } else {
                NetworkResult.Success(response.body()!!)
            }
        }
        else {
//
//            val errorBody = response.errorBody()?.string() ?: ""
            NetworkResult.Error("errorBody")

        }
    }

}