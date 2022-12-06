package com.intern.evtutors.data.services

import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.BaseService
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.apis.QuizAPI
import com.intern.evtutors.data.model_json.QuizJson
import javax.inject.Inject

class QuizServices @Inject constructor(
    private val quizAPI: QuizAPI
):BaseRemoteService() {
    suspend fun getAllQuizByLessonId(lessonId:Int):NetworkResult<MutableList<QuizJson>> {
        return callApi { quizAPI.getAllQuizByIdLesson(lessonId) }
    }

    suspend fun insertQuiz(quizJson: QuizJson):NetworkResult<QuizJson> {
        return  callApi { quizAPI.postQuiz(quizJson) }
    }
}