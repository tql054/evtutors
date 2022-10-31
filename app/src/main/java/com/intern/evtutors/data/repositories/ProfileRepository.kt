package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.services.ProfileServices
import com.intern.evtutors.di.IoDispatcher
import com.intern.evtutors.di.MainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileServices: ProfileServices,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    suspend fun getCertificates(idTutor:Int) = withContext(dispatcher) {
        when(val result = profileServices.getAllCertificate(1234)) { //fake idTutor
            is NetworkResult.Success -> {
                result.data.toCertificates()
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun putCertificates(idTutor: Int, certificates: Certificates) = withContext(dispatcher) {
        when(val result = profileServices.putCertificates(1234, certificates)) { //fake idTutor
            is NetworkResult.Success -> {
                result.data.toCertificates()
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
}