package com.intern.evtutors.data.models

class Question (
    val id:String? = "",
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
)