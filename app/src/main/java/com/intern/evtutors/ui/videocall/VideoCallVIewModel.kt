package com.intern.evtutors.ui.videocall

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.model_json.TokenJson
import com.intern.evtutors.data.models.AppInfo
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.data.repositories.AppInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class VideoCallVIewModel @Inject constructor(private val appInfoRepository: AppInfoRepository):BaseViewModel() {
    private var _token = MutableLiveData<TokenJson>()
    val token : LiveData<TokenJson>
        get() = _token

    private var _appInfo = MutableLiveData<AppInfo>()
    val appInfo : LiveData<AppInfo>
        get() = _appInfo

    fun fetchData(channelName:String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            _appInfo.value = appInfoRepository.getAppInfo()
            if(appInfo.value!!.appID != null) {
                val data = appInfoRepository.getToken(appInfo.value!!.appID, appInfo.value!!.appCertificate, channelName)
                _token.value = data
            }
        }
    }

    fun finishJob() {
        registerJobFinish()
    }
}