package com.intern.evtutors.data.services




import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.BaseRemoteService2
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.base.network.NetworkResult2
import com.intern.evtutors.data.apis.LoginAPI
import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.getjwtToken
import javax.inject.Inject


class JsonLoginServer @Inject constructor(private val jsonLessonAPI: LoginAPI):
    BaseRemoteService2() {
    suspend fun getAllAccount(account: Account): NetworkResult2<getjwtToken> {
        return callApi {jsonLessonAPI.getLesson(account) }
    }

}

//package com.intern.evtutors.data.services
//
//
//
//
//import com.intern.evtutors.base.network.BaseRemoteService
//import com.intern.evtutors.base.network.NetworkResult
//import com.intern.evtutors.data.apis.LoginAPI
//import com.intern.evtutors.data.models.Account
//import com.intern.evtutors.data.models.getjwtToken
//import javax.inject.Inject
//
//
//class JsonLoginServer @Inject constructor(private val jsonLessonAPI: LoginAPI):
//    BaseRemoteService() {
//    suspend fun getAllAccount(account: Account): NetworkResult<getjwtToken> {
//        return callApi {jsonLessonAPI.getLesson(account) }
//    }
//
//}