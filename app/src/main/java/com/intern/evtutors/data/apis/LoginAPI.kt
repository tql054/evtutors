package com.intern.evtutors.data.apis


import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken

import retrofit2.Response
import retrofit2.http.*

interface   LoginAPI {
    @POST("/api/authenticate")
    suspend fun getLesson(
        @Body  account: Account
    ): Response<getjwtToken>


    @PUT("/api/updateUserInfo/idUser={idUser}")
    suspend fun UpdataInfo(
       @Path("idUser") idUser:Int,
       @Body user:User
    ): Response<User>
}