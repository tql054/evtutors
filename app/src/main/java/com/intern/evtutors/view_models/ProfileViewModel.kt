package com.intern.evtutors.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.repositories.AppInfoRepository
import com.intern.evtutors.data.repositories.LessonRepository
import com.intern.evtutors.data.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
):BaseViewModel() {
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    var isUpdating by mutableStateOf(false)
    var certificates by mutableStateOf(mutableListOf<String?>())
    fun toggleUpdating() {
        isUpdating = !isUpdating
    }

    fun fetchCetificate(idTutor:Int) {
        viewModelScope.launch(handler) {
            Log.d("result", "start")
            val result = profileRepository.getCertificates(idTutor)
            val listResult = mutableListOf<String?>(result.img1, result.img2, result.img3, result.img4, result.img5)
            certificates = listResult
        }
    }

    fun addCertificate(certificate:String) {
        certificates.forEachIndexed {
            index: Int, s: String? ->
            if (s=="") {
                certificates[index] = certificate
                Log.d("file path: ", certificates.toString())
                return
            }
        }
    }
}