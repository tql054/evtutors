package com.intern.evtutors.base.network

import android.util.Log
import retrofit2.Response

open class BaseRemoteService : BaseService() {

    protected suspend fun <T : Any> callApi(call: suspend () -> Response<T>): NetworkResult<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            Log.d("abc", t.toString())
            return NetworkResult.Error(parseNetworkErrorException(t))
        }
        return if (response.isSuccessful) {
            if (response.body() == null) {
                Log.d("abc", "b")
                NetworkResult.Error(BaseNetworkException(responseMessage =  "Response without body", responseCode = 200))
            } else {
                NetworkResult.Success(response.body()!!)
            }
        }
        else {
//            NetworkResult.Success(response.body()!!)

            Log.d("abc", "c")
            val errorBody = response.errorBody()?.string() ?: ""
                NetworkResult.Error(parseError(response.message(), response.code(), errorBody))
            //NetworkResult.Error(parseError(response.message(), response.code(), errorBody))
        }
    }

}