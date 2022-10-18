package com.intern.evtutors.data.model_json

import com.intern.evtutors.data.models.Course

data class CourseJson(
    val id:Int,
    val name:String,
    val idTeacher:Int,
    val idStudent:Int,
    val tutorFee:String,
    val dateCreated:String
) {
    fun toCourse():Course {
        return Course(
            id = id,
            name = name,
            idTeacher = idTeacher,
            idStudent = idStudent,
            tutorFee = tutorFee,
            dateCreated = dateCreated
        )
    }

}