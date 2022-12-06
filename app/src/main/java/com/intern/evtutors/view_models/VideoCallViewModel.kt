package com.intern.evtutors.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.model_json.LessonJson
import com.intern.evtutors.data.model_json.TokenJson
import com.intern.evtutors.data.models.AppInfo
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.data.repositories.AppInfoRepository
import com.intern.evtutors.data.repositories.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class VideoCallVIewModel @Inject constructor(private val appInfoRepository: AppInfoRepository,
                                             private val lessonRepository: LessonRepository):BaseViewModel() {
    private var _token = MutableLiveData<TokenJson>()
    val token : LiveData<TokenJson>
        get() = _token

    private var _lesson = MutableLiveData<Lesson>()
    val lesson : LiveData<Lesson>
        get() = _lesson

    private var _appInfo = MutableLiveData<AppInfo>()
    val appInfo : LiveData<AppInfo>
        get() = _appInfo

    private var _response = MutableLiveData<JSONObject>()
    val response : LiveData<JSONObject>
        get() = _response

    fun fetchData(channelName:String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            _appInfo.value = appInfoRepository.getAppInfo()
            if(appInfo.value!!.appID != null) {
//                val data = appInfoRepository.getToken(appInfo.value!!.appID, appInfo.value!!.appCertificate, channelName)
//                _token.value = data
            }
        }
    }

    fun updateBeganLesson(id:Int) {
        parentJob = viewModelScope.launch(handler) {
            var lessonUpdated = lessonRepository.getLessonById(id)
//            if(lessonUpdated.status != "1") {
//                lessonUpdated.status = "1"
//                lessonRepository.updateLesson(id, lessonUpdated)
//            }
        }
    }

    fun updateEndedLesson(id:Int, totalDuration: Int) {
        parentJob = viewModelScope.launch(handler) {
//            var lessonUpdated = lessonRepository.getLessonById(id)
//            val date = Date()
//            if(lessonUpdated.realTimeStart == "0000-00-00 00:00:00") {
//                lessonUpdated.realTimeStart = getRealTimeStart(totalDuration, date)
//            }
//            lessonUpdated.status = "2"
//            lessonUpdated.realTimeEnd = formatDateTime(date)
//            Log.d("Lesson tab", "start")
//            lessonRepository.updateLesson(id, lessonUpdated)
        }
    }

    private fun getRealTimeStart(totalDuration:Int, date:Date):String {
        val result = Date(date.time - (totalDuration*1000))
        return formatDateTime(result)
    }

    private fun formatDateTime(date:Date):String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-M-dd hh:mm:ss")
        return dateFormat.format(date)
    }

    fun finishJob() {
        registerJobFinish()
    }
}