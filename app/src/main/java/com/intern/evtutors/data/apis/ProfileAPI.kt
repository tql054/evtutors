package com.intern.evtutors.data.apis



import com.intern.evtutors.data.model_json.CertificateJson
import com.intern.evtutors.data.model_json.TeacherDegree
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

    @GET("/api/user/{userId}/degrees/status/{status}")
    suspend fun getDegreeTutor(
        @Path("userId") userId:Int,
        @Path("status") status:Int
    ):Response<MutableList<TeacherDegree>>

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


    @GET("/api/user/{userId}/degrees")
    suspend fun getCertificates(
        @Path("userId") userId:Int
    ):Response<MutableList<CertificateJson>>

    @POST("/api/user/{userId}/degrees")
    suspend fun putCertificates(
        @Path("userId") userId:Int,
        @Body certificate: CertificateJson
    ):Response<CertificateJson>

    @DELETE("/api/degree/{id}")
    suspend fun deleteCertificates(
        @Path("id") id:Int
    ):Response<Any>
}