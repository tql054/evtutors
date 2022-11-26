package com.intern.evtutors.data.models

import java.io.Serializable

class Lesson(
    val id:Int,
    val courseId:Int,
//    val channelName:String,
//    val idTeacher:Int,
//    val idStudent:Int,
    val status:String,
    val timeStart:String,
    val timeEnd:String,
//    val realTimeStart:String,
//    val realTimeEnd:String
):Serializable {
}