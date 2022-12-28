package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.model_json.QuizJson
import com.intern.evtutors.data.services.QuizServices
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val quizServices: QuizServices,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAllQuizByLessonId(lessonId:Int) = withContext(dispatcher) {
        when(val result = quizServices.getAllQuizByLessonId(lessonId)) {
            is NetworkResult.Success -> {
                result.data.map {
                    it.toQuiz()
                }
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun insertQuiz(quizJson: QuizJson) = withContext(dispatcher) {
        when(val result = quizServices.insertQuiz(quizJson)) {
            is NetworkResult.Success -> {
                result.data
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun updateQuiz(quizId:Int, quizJson: QuizJson) = withContext(dispatcher) {
        when(val result = quizServices.updateQuiz(quizId, quizJson)) {
            is NetworkResult.Success -> {
                result.data.toQuiz()
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
}