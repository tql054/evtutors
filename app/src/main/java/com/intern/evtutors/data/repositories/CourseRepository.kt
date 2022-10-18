package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.models.Course
import com.intern.evtutors.data.services.CourseServices
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val courseServices: CourseServices,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAvailableCourses() = withContext(dispatcher) {
        when(val result = courseServices.getAvailableCourses()) {
            is NetworkResult.Success -> {
                var listResults = mutableListOf<Course>()
                result.data.map {
                    listResults.add(it.toCourse())
                }
                listResults
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
}