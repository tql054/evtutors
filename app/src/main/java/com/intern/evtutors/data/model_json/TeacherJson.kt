package com.intern.evtutors.data.model_json

import com.intern.evtutors.data.models.Teacher

class TeacherJson(
    val idTeacher:String,
    val name:String,
    val age: String,
    val gender: String,
    val address: String,
    val phone: String,
    val avatar: String,
    val email: String,
    val userName:String,
    val password: String
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
            email = email,
            userName = userName,
            password = password
        )
    }
}