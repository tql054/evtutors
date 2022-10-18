package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.CourseJson
import retrofit2.Response
import retrofit2.http.GET

interface CourseAPI {
    @GET()
    fun getAvailableCourses():Response<MutableList<CourseJson>>
}