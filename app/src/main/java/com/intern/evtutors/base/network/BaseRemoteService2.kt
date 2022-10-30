package com.intern.evtutors.base.network

import android.util.Log
import com.intern.evtutors.data.models.getjwtToken
import retrofit2.Response

open class BaseRemoteService2 : BaseService() {
    val data: getjwtToken?=null
    protected suspend fun <T : Any> callApi(call: suspend () -> Response<T>): NetworkResult2<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            return NetworkResult2.Error("data")
        }
        return if (response.isSuccessful) {
            if (response.body() == null) {
                NetworkResult2.Error("")
            } else {
                NetworkResult2.Success(response.body()!!)
            }
        }
        else {
//            NetworkResult.Success(response.body()!!)
            val errorBody = response.errorBody()?.string() ?: ""
                NetworkResult2.Error(errorBody)
            //NetworkResult.Error(parseError(response.message(), response.code(), errorBody))
        }
    }

}