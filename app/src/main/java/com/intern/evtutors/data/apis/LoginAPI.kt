package com.intern.evtutors.data.apis


import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface   LoginAPI {
    @POST("/api/authenticate")
    suspend fun getLesson(
        @Body  account: Account
    ): Response<getjwtToken>


    @POST("/api/updateUserInfo/idUser={idUser}")
    suspend fun UpdataInfo(
       @Path("idUser") idUser:Int,
       @Body user:User
    ): Response<User>
}