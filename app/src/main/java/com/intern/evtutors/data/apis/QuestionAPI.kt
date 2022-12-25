package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.QuestionJson
import com.intern.evtutors.data.models.Question
import retrofit2.Response
import retrofit2.http.*

interface QuestionAPI {
    @GET("/api/question/quizId={quizId}")
    suspend fun getAllQuestionByQuizId(
        @Path("quizId") quizId:Int
    ):Response<MutableList<QuestionJson>>

    @POST("/api/addQuestion")
    suspend fun insertQuestion(
        @Body question: Question
    ):Response<QuestionJson>

    @PUT("/api/updateQuestion/{id_question}")
    suspend fun updateQuestion(
        @Path("id_question") id_question:Int,
        @Body question: Question
    ):Response<QuestionJson>
}