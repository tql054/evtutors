package com.intern.evtutors.data.model_json

import com.intern.evtutors.data.models.Teacher

class TeacherJson(
    val idTeacher:Int,
    val name:String,
    val age: Int,
    val gender: Int,
    val address: String,
    val phone: String,
    val avatar: String,
    val email: String,
) {
    fun toTeacher(): Teacher {
        return Teacher(
            idTeacher = idTeacher,
            name = name,
            age = age,
            gender = gender,
            address = address,
            phone = phone,
            avatar = avatar,
            email = email
        )
    }
}