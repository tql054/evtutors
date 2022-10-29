package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.CertificatesJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileAPI {
    @GET("/api/CertificatesOfTeacher/idTeacher={id}")
    suspend fun getCertificates(
        @Path("id") id:Int
    ):Response<CertificatesJson>
}