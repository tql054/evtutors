package com.intern.evtutors.data.model_json

import com.intern.evtutors.data.models.Quiz

class QuizJson(
    val id:Int,
    val title:String,
    val lessonId:Int
) {
    fun toQuiz():Quiz {
        return Quiz(
            id = id,
            title = title,
            lessonId = lessonId
        )
    }
}