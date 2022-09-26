package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
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
                throw result.exception
            }
        }
    }

    suspend fun getLessonById(id:Int) = withContext(dispatcher) {
        when(val result = lessonServices.getLessonById(id)) {
            is NetworkResult.Success -> result.data.toLesson()
            is NetworkResult.Error -> result.exception
        }
    }

}