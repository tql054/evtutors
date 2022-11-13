package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.model_json.CertificateJson
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
        when(val result = profileServices.getAllCertificate(idTutor)) { //fake idTutor
            is NetworkResult.Success -> {
                result.data
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun putCertificates(idTutor: Int, certificates: CertificateJson) = withContext(dispatcher) {
        when(val result = profileServices.putCertificates(idTutor, certificates)) { //fake idTutor
            is NetworkResult.Success -> {
                result.data.toCertificates()
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun deleteCertificates(id: Int) = withContext(dispatcher) {
        when(val result = profileServices.deleteCertificates(id)) { //fake idTutor
            is NetworkResult.Success -> {
                result.data
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

}