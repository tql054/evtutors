package com.intern.evtutors.data.models

class Question (
    var id:Int?=0,
    var quizId:Long? = 1,
    var question:String? = "",
    var answer_a: String? = "",
    var status_a: Boolean? = false,
    var answer_b: String? = "",
    var status_b: Boolean? = false,
    var answer_c: String? = "",
    var status_c: Boolean? = false,
    var answer_d: String? = "",
    var status_d: Boolean? = false
) {
    fun updateAnswerList(answerA:Answer, answerB:Answer ,answerC:Answer, answerD:Answer,) {
//        answer_a = answerA
    }
}