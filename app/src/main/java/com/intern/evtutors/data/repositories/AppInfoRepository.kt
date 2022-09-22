package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.services.AppInfoServices
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppInfoRepository @Inject constructor(
    private val appInfoServices: AppInfoServices,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) {
    suspend fun getAppInfo() = withContext(dispatcher) {
        when(val result = appInfoServices.getAppInfo()) {
            is NetworkResult.Success -> {
                result.data.toAppInfo()
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getToken(appID:String, appCertificate:String, channelName:String) = withContext(dispatcher) {
        when(val result = appInfoServices.getToken(appID, appCertificate, channelName)) {
            is NetworkResult.Success -> {
                result.data
            }

            is NetworkResult.Error -> {
                result.exception
            }
        }
    }
}