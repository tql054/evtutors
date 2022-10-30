package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.CertificatesJson
import com.intern.evtutors.data.models.Certificates
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileAPI {
    @GET("/api/CertificatesOfTeacher/idTeacher={id}")
    suspend fun getCertificates(
        @Path("id") id:Int
    ):Response<CertificatesJson>

    @PUT("/api/updateDegree/idTeacher={id}")
    suspend fun putCertificates(
        @Path("id") id:Int,
        @Body certificates: Certificates
    ):Response<CertificatesJson>
}