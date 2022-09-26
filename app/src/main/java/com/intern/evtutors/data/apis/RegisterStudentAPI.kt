package com.intern.evtutors.data.apis

import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RegisterStudentAPI {
    @POST("/api/registerNewStudent")
    suspend fun toRegitser(
        @Body user: User
    ): Response<UserJson>
}