package com.intern.evtutors.data.model_json

import com.intern.evtutors.data.models.Answer
import com.intern.evtutors.data.models.Question

class QuestionJson(
    val id:Int? = 0,
    val quizId:Long? = 1,
    val question:String? = "",
    val answer_a: String? = "",
    val status_a: Boolean? = false,
    val answer_b: String? = "",
    val status_b: Boolean? = false,
    val answer_c: String? = "",
    val status_c: Boolean? = false,
    val answer_d: String? = "",
    val status_d: Boolean? = false
) {
    fun toQuestion(): Question {
        return Question(
            id = id,
            quizId=quizId,
            question=question,
            answer_a=answer_a,
            status_a=status_a,
            answer_b=answer_b,
            status_b=status_b,
            answer_c=answer_c,
            status_c=status_c,
            answer_d=answer_d,
            status_d=status_d,
        )
    }
}