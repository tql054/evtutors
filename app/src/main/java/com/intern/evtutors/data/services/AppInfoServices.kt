package com.intern.evtutors.data.services

import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.apis.AppInfoAPI
import com.intern.evtutors.data.model_json.AppInfoJson
import com.intern.evtutors.data.model_json.TokenJson
import javax.inject.Inject

class AppInfoServices @Inject constructor(private val appInfoAPI: AppInfoAPI):BaseRemoteService() {
    suspend fun getAppInfo():NetworkResult<AppInfoJson> {
        return callApi { appInfoAPI.getAppInfo() }
    }

    suspend fun getToken(appID:String, appCertificate:String, channelName:String):NetworkResult<TokenJson> {
        return callApi { appInfoAPI.getToken(appID, appCertificate, channelName)}
    }
}