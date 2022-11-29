package com.intern.evtutors.data.model_json

import com.intern.evtutors.data.models.Lesson

data class LessonJson(
    val id:Int,
    val courseId:Int,
//    val channelName:String,
//    val idTeacher:Int,
//    val idStudent:Int,
    val status:Int,
    val timeStart:String,
    val timeEnd:String,
//    val realTimeStart:String,
//    val realTimeEnd:String
) {
    fun toLesson(): Lesson {
        return Lesson(
//            id,channelName, idTeacher, idStudent, timeStart,
//            timeEnd, status, realTimeStart, realTimeEnd
            id,courseId, status,timeStart,
            timeEnd
        )
    }
}