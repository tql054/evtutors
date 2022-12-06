package com.intern.evtutors.data.services

import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.apis.QuestionAPI
import com.intern.evtutors.data.model_json.QuestionJson
import javax.inject.Inject

class QuestionServices @Inject constructor(
    private val questionAPI: QuestionAPI
): BaseRemoteService() {
    suspend fun getAllQuizByLessonId(quizId:Int): NetworkResult<MutableList<QuestionJson>> {
        return callApi { questionAPI.getAllQuestionByQuizId(quizId) }
    }
}