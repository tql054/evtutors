package com.intern.evtutors.data.apis


import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.getjwtToken

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface   LoginAPI {
    @POST("/api/authenticate")
    suspend fun getLesson(
        @Body  account: Account
    ): Response<getjwtToken>
}