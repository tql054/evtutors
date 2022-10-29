package com.intern.evtutors.data.services

import android.util.Log
import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.BaseService
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.apis.ProfileAPI
import com.intern.evtutors.data.model_json.CertificatesJson
import javax.inject.Inject

class ProfileServices @Inject constructor(
    private val profileAPI: ProfileAPI
):BaseRemoteService() {
    suspend fun getAllCertificate(idTutor:Int):NetworkResult<CertificatesJson> {
        return callApi { profileAPI.getCertificates(idTutor) }
    }
}