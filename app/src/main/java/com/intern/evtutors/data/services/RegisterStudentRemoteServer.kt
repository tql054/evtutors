package com.intern.evtutors.data.services


import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.BaseRemoteService2
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.base.network.NetworkResult2
import com.intern.evtutors.data.apis.RegisterStudentAPI
import com.intern.evtutors.data.apis.RegisterTeacherAPI
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.User

import javax.inject.Inject



class RegisterStudentRemoteServer @Inject constructor(private val registerStudentAPI: RegisterStudentAPI):
    BaseRemoteService2() {
    suspend fun todoRegister(user: User): NetworkResult2<UserJson> {
        return callApi {registerStudentAPI.toRegitser(user) }
    }

}