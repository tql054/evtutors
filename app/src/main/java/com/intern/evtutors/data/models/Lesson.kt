package com.intern.evtutors.data.models

import java.io.Serializable

class Lesson(
    val id:Int,
    val channelName:String,
    val idTeacher:Int,
    val idStudent:Int,
    val timeStart:String,
    val timeEnd:String,
    var status:String,
    var realTimeStart:String,
    var realTimeEnd:String
):Serializable {
}