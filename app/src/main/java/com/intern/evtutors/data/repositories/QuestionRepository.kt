package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.models.Question
import com.intern.evtutors.data.services.QuestionServices
import com.intern.evtutors.data.services.QuizServices
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionServices: QuestionServices,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAllQuestionByQuizId(quizId:Int) = withContext(dispatcher) {
        when(val result = questionServices.getAllQuizByLessonId(quizId)) {
            is NetworkResult.Success -> {
                result.data.map {
                    it.toQuestion()
                }
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun insertQuestion(question: Question) = withContext(dispatcher) {
        when(val result = questionServices.insertQuestion(question)) {
            is NetworkResult.Success -> {
                result.data.toQuestion()
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
}