package com.intern.evtutors.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.data.repositories.AppInfoRepository
import com.intern.evtutors.data.repositories.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val  lessonRepository: LessonRepository) : BaseViewModel() {


    private var _lesson = MutableLiveData<List<Lesson>>()
    val lesson : LiveData<List<Lesson>>
        get() = _lesson

    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
//            val data = appInfoRepository.getAppInfo()
//            val data2 = appInfoRepository.getToken(data.appID, data.appCertificate, "main")
            val data = lessonRepository.getAllLesson()
            _lesson.postValue(data)
        }
        registerJobFinish()
    }
}