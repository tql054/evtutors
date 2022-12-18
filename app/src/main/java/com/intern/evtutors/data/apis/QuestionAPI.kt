package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.QuestionJson
import com.intern.evtutors.data.models.Question
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuestionAPI {
    @GET("/api/question/quizId={quizId}")
    suspend fun getAllQuestionByQuizId(
        @Path("quizId") quizId:Int
    ):Response<MutableList<QuestionJson>>

    @POST("/api/addQuestion")
    suspend fun insertQuestion(
        @Body question: Question
    ):Response<QuestionJson>
}