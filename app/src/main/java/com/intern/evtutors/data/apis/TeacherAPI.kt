package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.TeacherJson
import retrofit2.Response
import retrofit2.http.GET

interface TeacherAPI {
    @GET
    fun getTodayTeachersForStudent():Response<MutableList<TeacherJson>>
}