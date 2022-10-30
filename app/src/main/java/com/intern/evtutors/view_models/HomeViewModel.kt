package com.intern.evtutors.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.Course
import com.intern.evtutors.data.models.Teacher
import com.intern.evtutors.data.repositories.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val courseRepository: CourseRepository):BaseViewModel() {
    private var _course = MutableLiveData<MutableList<Course>>()
    val course : LiveData<MutableList<Course>>
        get() = _course

    private var _tutors = MutableLiveData<MutableList<Teacher>>()
    val tutors : LiveData<MutableList<Teacher>>
        get() = _tutors

    override fun fetchData() {
        val teacherList =  mutableListOf<Teacher>(
            Teacher(1, "Le Tuan", 21, 1, "11", "0", "sdf", "dfs"),
            Teacher(2, "Van Minh", 21, 1, "11", "0", "sdf", "dfs"),
            Teacher(3, "Nguyen Van Hung", 21, 1, "11", "0", "sdf", "dfs"),
            Teacher(4, "Pham Tan", 21, 1, "11", "0", "sdf", "dfs"),
            Teacher(5, "Do Truong", 21, 1, "11", "0", "sdf", "dfs")
        )
        parentJob = viewModelScope.launch(handler) {
            val data = courseRepository.getAvailableCourses()
            _course.postValue(data)
            _tutors.postValue(teacherList)
        }
    }
}