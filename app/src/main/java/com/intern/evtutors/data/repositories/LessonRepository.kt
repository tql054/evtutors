package com.intern.evtutors.data.repositories

import android.util.Log
import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.model_json.LessonJson
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.data.services.LessonServices
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val lessonServices: LessonServices,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) {
    suspend fun getAllLesson() = withContext(dispatcher) {
        when(val result = lessonServices.getAllLesson()) {
            is NetworkResult.Success -> {
               result.data.map {
                    it.toLesson()
               }
            }

            is NetworkResult.Error -> {
                null
            }
        }
    }

    suspend fun getAllLessonByDate(typeOfUser:String, idUser:Int, date:String) = withContext(dispatcher) {
        val result:NetworkResult<MutableList<LessonJson>> = when(typeOfUser) {
            "Student" -> lessonServices.getAllStudentsLessonByDate(idUser, date)
            else -> {
                lessonServices.getAllTeachersLessonByDate(idUser, date)
            }
        }
        when(result) {
            is NetworkResult.Success -> {
                result.data.map {
                    it.toLesson()
                }
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }

    }

    suspend fun getLessonById(id:Int) = withContext(dispatcher) {
        when(val result = lessonServices.getLessonById(id)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun updateLesson(id:Int, lesson:Lesson) = withContext(dispatcher) {
        when(val result = lessonServices.updateLesson(id, lesson)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> {
                null
            }
        }
    }

}