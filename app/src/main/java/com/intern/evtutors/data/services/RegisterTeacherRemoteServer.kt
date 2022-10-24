package com.intern.evtutors.data.services


import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.BaseRemoteService2
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.base.network.NetworkResult2
import com.intern.evtutors.data.apis.RegisterTeacherAPI
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.User

import javax.inject.Inject



class RegisterTeacherRemoteServer @Inject constructor(private val registerTeacherAPI: RegisterTeacherAPI):
    BaseRemoteService2() {
    suspend fun todoRegister(user: User): NetworkResult2<UserJson> {
        return callApi {registerTeacherAPI.toRegitser(user) }
    }

}