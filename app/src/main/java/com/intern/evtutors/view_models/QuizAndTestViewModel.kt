package com.intern.evtutors.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.model_json.QuizJson
import com.intern.evtutors.data.models.Answer
import com.intern.evtutors.data.models.Question
import com.intern.evtutors.data.models.Quiz
import com.intern.evtutors.data.repositories.QuestionRepository
import com.intern.evtutors.data.repositories.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizAndTestViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val questionRepository: QuestionRepository
):BaseViewModel() {
    var stateListQuiz by mutableStateOf(mutableListOf<Quiz>())
    var stateListQuestion by mutableStateOf(mutableListOf<Question>())
    var stateListAnswer by mutableStateOf(listOf(
        Answer("", false),
        Answer("", false),
        Answer("", false),
        Answer("", false)
    ))

    var stateQuizTitle by mutableStateOf("")
    var stateCurrentQuestion by mutableStateOf(Question())
    var stateCurrentAnswer by mutableStateOf<AnswerItem?>(null)

    var stateAddQuestion by mutableStateOf(false)

    fun switchAddingQuestion(status:Boolean) {
        stateAddQuestion = status
    }

    fun switchAddingAnswer(index:Int, answer:Answer) {
        stateCurrentAnswer = AnswerItem(index, answer)
    }

    fun getAllQuiz(lessonId:Int) {
        viewModelScope.launch(handler) {
            val result = mutableListOf<Quiz>()
            quizRepository.getAllQuizByLessonId(lessonId).map {
                result.add(it)
            }
            if(result.isNotEmpty()) {
                stateListQuiz = result
            }
        }
    }

    fun addQuiz(newQuiz: Quiz) {
        // compulsory to call api, insert directory
    }

    fun getAllQuestion(quizId:Int) {
        viewModelScope.launch(handler) {
            val result = mutableListOf<Question>()

            questionRepository.getAllQuestionByQuizId(quizId).map {
                result.add(it)
            }
            Log.d("state questionlist :", stateListQuestion.toString())

            if(result.isNotEmpty()) {
                stateListQuestion = result
            }
        }
    }

    fun addQuestion(newQuestion: Question) {
        val newListQuestion = mutableListOf<Question>()
        for(question in stateListQuestion) newListQuestion.add(question)
        newListQuestion.add(newQuestion)
        stateListQuestion = newListQuestion
    }

    fun addAnswer(index:Int, newAnswer:String, status:Boolean) {
        val newListAnswer = mutableListOf<Answer>()
        for(answer in stateListAnswer) newListAnswer.add(answer)
        newListAnswer[index] = Answer(newAnswer,status)
        stateListAnswer = newListAnswer
    }

    data class AnswerItem(
        val index: Int?=0,
        val answer: Answer?=Answer()
    )
}