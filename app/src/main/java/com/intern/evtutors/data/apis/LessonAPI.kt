package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.LessonJson
import com.intern.evtutors.data.models.Lesson
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface LessonAPI {
    @GET("/api/lessons")
    suspend fun getAllLesson():Response<MutableList<LessonJson>>

    @GET("/api/lessons/{id}")
    suspend fun getLessonById(
        @Path("id") id:Int,
        // put body into request
    ):Response<LessonJson>

    @PUT("api/lessons/status/{id}")
    suspend fun updateLesson(lesson: Lesson):Response<JSONObject>
}