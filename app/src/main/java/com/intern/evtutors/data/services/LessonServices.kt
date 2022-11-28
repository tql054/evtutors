package com.intern.evtutors.data.services

import android.util.Log
import com.intern.evtutors.base.network.BaseRemoteService
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.apis.LessonAPI
import com.intern.evtutors.data.model_json.LessonJson
import com.intern.evtutors.data.models.Lesson
import org.json.JSONObject
import javax.inject.Inject


class LessonServices @Inject constructor(private val lessonAPI: LessonAPI):BaseRemoteService() {
    //must be provided lesson list by week
    suspend fun getAllLesson():NetworkResult<MutableList<LessonJson>> {
        return callApi { lessonAPI.getAllLesson() }
    }

    suspend fun getAllTeachersLessonByDate(idTeacher:Int, date:String):NetworkResult<MutableList<LessonJson>> {
        return callApi { lessonAPI.getAllTeachersLessonByDate(idTeacher, date) }
    }

    suspend fun getAllStudentsLessonByDate(idStudent:Int, date:String):NetworkResult<MutableList<LessonJson>> {
        return callApi { lessonAPI.getAllStudentsLessonByDate(idStudent, date) }
    }

    suspend fun getLessonById(id:Int):NetworkResult<LessonJson> {
        return callApi { lessonAPI.getLessonById(id) }
    }

    suspend fun updateLesson(id:Int, lesson:Lesson):NetworkResult<LessonJson> {
        return callApi { lessonAPI.updateLesson(id, lesson) }
    }
}