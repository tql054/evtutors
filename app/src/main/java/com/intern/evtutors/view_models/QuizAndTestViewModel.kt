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
    var stateListQuestion by mutableStateOf<MutableList<Question>?>(null)
    var stateListAnswer by mutableStateOf<MutableList<Answer>?>(null)

    var stateQuizInput by mutableStateOf("")
    var stateQuestionInput by mutableStateOf("")
    var stateAnswerInput by mutableStateOf("")
    var stateIndexCurrentQuestion by mutableStateOf<Int?>(null)
    var stateIndexCurrentAnswer by mutableStateOf<Int?>(null)
    var stateAddQuestion by mutableStateOf("disable") // adding-editing-disable

    fun switchAddingQuestion(status:String, index:Int?) {
        stateAddQuestion = status
        stateIndexCurrentQuestion = index
        stateListAnswer = null
    }

    fun switchAddingAnswer(index:Int?) {
        stateIndexCurrentAnswer = index
    }

    fun getOpeningQuestionBoxStatus():Boolean {
        if(stateAddQuestion=="disable") return false
        return true
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

    fun addQuiz(lessonId: Int) {
        viewModelScope.launch(handler) {
            val newQuiz = quizRepository.insertQuiz(QuizJson(0, stateQuizInput, lessonId))
            if(newQuiz != null) {
                val listQuestion = stateListQuestion
                listQuestion?.let {
                    for (question in listQuestion) {
                        question.quizId = newQuiz.id.toLong()
                        questionRepository.insertQuestion(question)
                    }
                }
            }
            Log.d("state new quiz", newQuiz.toString())
        }
    }

    fun getAllQuestion(quizId:Int) {
        viewModelScope.launch(handler) {
            val result = mutableListOf<Question>()

            questionRepository.getAllQuestionByQuizId(quizId).map {
                result.add(it)
            }
            stateListQuestion = result
        }
    }

    fun getCurrentQuestion():Question {
        return if(stateIndexCurrentQuestion!=null)
            stateListQuestion!![stateIndexCurrentQuestion!!]
        else
            Question()
    }

    fun getListAnswer() {
        val question = getCurrentQuestion()
        stateListAnswer = mutableListOf(
            Answer(answer = question.answer_a, status = question.status_a),
            Answer(answer = question.answer_b, status = question.status_b),
            Answer(answer = question.answer_c, status = question.status_c),
            Answer(answer = question.answer_d, status = question.status_d)
        )
    }

    fun getCurrentAnswer():Answer {
        if(stateListAnswer!=null)
            return stateListAnswer!![stateIndexCurrentAnswer!!]
        return Answer()
    }

    fun addQuestion(quizId: Int?) {
        val newListQuestion = mutableListOf<Question>()
        val newQuestion = generateNewQuestion()
        newQuestion.question = stateQuestionInput
        newQuestion.quizId = quizId?.toLong()?:null
        stateListQuestion?.let {
            if(stateIndexCurrentQuestion==null) {
                for(question in stateListQuestion!!) newListQuestion.add(question)
                newListQuestion.add(newQuestion)
            } else {
                stateListQuestion!!.forEachIndexed { index, question ->
                    if(stateIndexCurrentQuestion == index) {
                        newListQuestion.add(newQuestion)
                    } else {
                        newListQuestion.add(question)
                    }
                }
            }
            stateListQuestion = newListQuestion
            switchAddingQuestion("disable", null)
        }
    }

    private fun generateNewQuestion():Question {
        val result = Question()
        stateListAnswer?.let {
            result.question = stateQuestionInput
            result.answer_a = stateListAnswer!![0].answer
            result.status_a = stateListAnswer!![0].status
            result.answer_b = stateListAnswer!![1].answer
            result.status_b = stateListAnswer!![1].status
            result.answer_c = stateListAnswer!![2].answer
            result.status_c = stateListAnswer!![2].status
            result.answer_d = stateListAnswer!![3].answer
            result.status_d = stateListAnswer!![3].status
        }
        return result
    }

    fun removeQuestion() {

    }

    fun addAnswer(answer:String, status:Boolean) {
        val newListAnswer = mutableListOf<Answer>()
        if(status) {
            for (answer in stateListAnswer!!) {
                newListAnswer.add(Answer(answer.answer, false))
            }
        }
        else {
            for (answer in stateListAnswer!!) {
                newListAnswer.add(answer)
            }
        }
        newListAnswer[stateIndexCurrentAnswer!!] = Answer(answer, status)
        stateListAnswer = newListAnswer
    }
}