package com.intern.evtutors.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.AppInfo
import com.intern.evtutors.data.repositories.AppInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val appInfoRepository: AppInfoRepository) : BaseViewModel() {
    private var _appInfo = MutableLiveData<AppInfo>()
    val appInfo : LiveData<AppInfo>
        get() = _appInfo

    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = appInfoRepository.getAppInfo()
            val data2 = appInfoRepository.getToken(data.appID, data.appCertificate, "main")
            _appInfo.postValue(data)
        }

        registerJobFinish()
    }
}