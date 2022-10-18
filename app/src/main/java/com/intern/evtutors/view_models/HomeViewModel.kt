package com.intern.evtutors.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.Course
import com.intern.evtutors.data.repositories.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val courseRepository: CourseRepository):BaseViewModel() {
    private var _course = MutableLiveData<MutableList<Course>>()
    val course : LiveData<MutableList<Course>>
        get() = _course

    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = courseRepository.getAvailableCourses()
            _course.postValue(data)
        }
        registerJobFinish()
    }
}