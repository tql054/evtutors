package com.intern.evtutors.data.repositories


import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.base.network.NetworkResult2
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken
import com.intern.evtutors.data.services.RegisterTeacherRemoteServer
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class jsonRegisterTeacherReposititory @Inject constructor(
    private val registerTeacherRemoteServer: RegisterTeacherRemoteServer,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){

    suspend fun toRegitser(user: User) = withContext(dispatcher){
        val data: UserJson?=null
        when (val result =registerTeacherRemoteServer.todoRegister(user)){

            is NetworkResult2.Success ->{

                result.data
                //true
            }
            is NetworkResult2.Error ->{
                data
            }
            NetworkResult2.Loading->{

            }
        }
    }

    suspend fun renerateCertificates(certificates: Certificates) = withContext(dispatcher) {
        val data: UserJson?=null
        when (val result =registerTeacherRemoteServer.generateCertificate(certificates)){
            is NetworkResult2.Success ->{
                result.data
            }

            is NetworkResult2.Error ->{
                data
            }

            NetworkResult2.Loading->{

            }
        }
    }
}
