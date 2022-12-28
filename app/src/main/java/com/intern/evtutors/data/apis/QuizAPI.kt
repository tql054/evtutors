package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.QuestionJson
import com.intern.evtutors.data.model_json.QuizJson
import com.intern.evtutors.data.models.Question
import com.intern.evtutors.data.models.Quiz
import retrofit2.Response
import retrofit2.http.*

interface QuizAPI {
    @GET("/api/quiz/id_lesson={id_lesson}")
    suspend fun getAllQuizByIdLesson(
        @Path("id_lesson") id_lesson:Int
    ):Response<MutableList<QuizJson>>

    @POST("/api/quiz")
    suspend fun postQuiz(
        @Body quiz: QuizJson
    ):Response<QuizJson>

    @PUT("/api/updateQuiz/id_quiz={quizId}")
    suspend fun updateQuiz(
        @Path("quizId") quizId:Int,
        @Body quiz: QuizJson
    ):Response<QuizJson>
}