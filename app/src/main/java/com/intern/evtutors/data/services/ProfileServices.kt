package com.intern.evtutors.data.services

import android.util.Log
import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.BaseService
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.base.network.NetworkResult2
import com.intern.evtutors.data.apis.ProfileAPI
import com.intern.evtutors.data.model_json.CertificatesJson
import com.intern.evtutors.data.model_json.TeacherDegree
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken
import javax.inject.Inject

class ProfileServices @Inject constructor(
    private val profileAPI: ProfileAPI
):BaseRemoteService() {
    suspend fun getAllAccount(account: Account): NetworkResult<getjwtToken> {
        return callApi {profileAPI.getAccount(account) }
    }

    suspend fun UpdateAccount(idUser: Int,user: User): NetworkResult<User> {
        return callApi {profileAPI.updateInfo(idUser,user) }
    }
    suspend fun getDegreeTutor(id: Int): NetworkResult<MutableList<TeacherDegree>> {
        return callApi {profileAPI.getDegreeTutor(id,2) }
    }


    suspend fun registerStudent(user: User): NetworkResult<UserJson> {
        return callApi {profileAPI.insertNewStudent(user) }
    }

    suspend fun registerTeacher(user: User): NetworkResult<UserJson> {
        return callApi {profileAPI.insertNewTeacher(user) }
    }

    suspend fun generateCertificate(certificates: Certificates):NetworkResult<Certificates> {
        Log.d("Return begin : ", certificates.toString())
        return callApi { profileAPI.generateCertificates(certificates) }
    }

    suspend fun getAllCertificate(idTutor:Int):NetworkResult<CertificatesJson> {
        return callApi { profileAPI.getCertificates(idTutor) }
    }

    suspend fun putCertificates(idTutor: Int, certificates: Certificates):NetworkResult<CertificatesJson> {
        return callApi { profileAPI.putCertificates(idTutor, certificates) }
    }
}