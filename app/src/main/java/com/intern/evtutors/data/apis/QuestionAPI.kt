package com.intern.evtutors.data.apis

import com.intern.evtutors.data.model_json.QuestionJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionAPI {
    @GET("/api/question/quizId={quizId}")
    suspend fun getAllQuestionByQuizId(
        @Path("quizId") quizId:Int
    ):Response<MutableList<QuestionJson>>
}