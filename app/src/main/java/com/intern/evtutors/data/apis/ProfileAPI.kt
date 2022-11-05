package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.CertificatesJson
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken
import retrofit2.Response
import retrofit2.http.*

interface ProfileAPI {
    @POST("/api/authenticate")
    suspend fun getAccount(
        @Body  account: Account
    ): Response<getjwtToken>

    @PUT("/api/updateUserInfo/idUser={idUser}")
    suspend fun updateInfo(
        @Path("idUser") idUser:Int,
        @Body user: User
    ): Response<User>


    @POST("/api/registerNewTeacher")
    suspend fun insertNewTeacher(
        @Body user: User
    ): Response<UserJson>


    @POST("/api/addDegree")
    suspend fun generateCertificates(
        @Body certificates: Certificates
    ): Response<Certificates>


    @POST("/api/registerNewStudent")
    suspend fun insertNewStudent(
        @Body user: User
    ): Response<UserJson>


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