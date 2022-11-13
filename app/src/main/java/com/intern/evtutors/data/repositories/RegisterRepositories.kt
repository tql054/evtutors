package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.base.network.NetworkResult2
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.services.ProfileServices
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepositories @Inject constructor(
    private val profileServices: ProfileServices,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    suspend fun registerStudent(user: User) = withContext(dispatcher){
        val data: UserJson?=null
        when (val result =profileServices.registerStudent(user)){
            is NetworkResult.Success ->{
                result.data
            }
            is NetworkResult.Error ->{
                null
            }
        }
    }

    suspend fun registerTeacher(user: User) = withContext(dispatcher){
        val data: UserJson?=null
        when (val result =profileServices.registerTeacher(user)){
            is NetworkResult.Success ->{
                result.data
            }
            is NetworkResult.Error ->{
               null
            }
        }
    }

    suspend fun generateCertificates(certificates: Certificates) = withContext(dispatcher) {
        val data: UserJson?=null
        when (val result =profileServices.generateCertificate(certificates)){
            is NetworkResult.Success ->{
                result.data
            }

            is NetworkResult.Error ->{
              null
            }
        }
    }
}
