package com.intern.evtutors.data.services

import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.apis.CourseAPI
import com.intern.evtutors.data.model_json.CourseJson
import javax.inject.Inject

class CourseServices @Inject constructor(
//    private val courseAPI: CourseAPI
):BaseRemoteService() {
    suspend fun getAvailableCourses():NetworkResult<MutableList<CourseJson>> {
//        return callApi {  }

//        Begin faking API
        val results = mutableListOf<CourseJson>(
            CourseJson(1, "Le Quoc Tuan", 123, 321, "1.000.000", "12/10/2022"),
            CourseJson(2, "Quang Tran", 123, 321, "2.000.000", "12/10/2022"),
            CourseJson(3, "Le Dang Khoa", 123, 321, "1.500.000", "12/10/2022"),
            CourseJson(4, "Vo Tan", 123, 321, "1.300.000", "12/10/2022"),
            CourseJson(5, "Pham Quoc Hung", 123, 321, "1.200.000", "12/10/2022"),
            )
        return NetworkResult.Success(results)
//        End faking API
    }
}