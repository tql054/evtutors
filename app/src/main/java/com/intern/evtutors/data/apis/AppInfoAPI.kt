package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.AppInfoJson
import com.intern.evtutors.data.model_json.TokenJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppInfoAPI {
    @GET("/api/agoraApp/id={id}")
    suspend fun getAppInfo(
        @Path("id") id:Int = 1
    ):Response<AppInfoJson>

    @GET("/api/generateToken/appID={appID}&appCertificate={appCertificate}&channelName={channelName}")
    suspend fun getToken(
        @Path("appID") appID:String,
        @Path("appCertificate") appCertificate:String,
        @Path("channelName") channelName:String
    ):Response<TokenJson>
}