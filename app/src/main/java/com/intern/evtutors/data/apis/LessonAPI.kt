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
    ):Response<LessonJson>

    @GET("/api/lessonByDate/id_teacher={id_teacher}&date={date}")
    suspend fun getAllTeachersLessonByDate(
        @Path("id_teacher") id_teacher:Int,
        @Path("date") date:String
    ):Response<MutableList<LessonJson>>

    @GET("/api/lessonByDate/id_student={id_student}&date={date}")
    suspend fun getAllStudentsLessonByDate(
        @Path("id_student") id_student:Int,
        @Path("date") date:String
    ):Response<MutableList<LessonJson>>


    @PUT("/api/lessons/status/{id}")
    suspend fun updateLesson(
        @Path("id") id:Int,
        @Body lesson:Lesson
    ):Response<LessonJson>
}