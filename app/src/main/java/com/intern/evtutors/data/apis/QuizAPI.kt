package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.QuizJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuizAPI {
    @GET("/api/quiz/id_lesson={id_lesson}")
    suspend fun getAllQuizByIdLesson(
        @Path("id_lesson") id_lesson:Int
    ):Response<MutableList<QuizJson>>

    @POST("/api/quiz")
    suspend fun postQuiz(
        @Body quiz: QuizJson
    ):Response<QuizJson>
}