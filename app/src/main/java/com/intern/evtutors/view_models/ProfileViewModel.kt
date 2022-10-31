package com.intern.evtutors.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.repositories.AppInfoRepository
import com.intern.evtutors.data.repositories.LessonRepository
import com.intern.evtutors.data.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.security.cert.Certificate
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
):BaseViewModel() {
    var stateUpdating by mutableStateOf(false)
    var stateAdding by mutableStateOf(false)
    var certificates by mutableStateOf(mutableListOf<String?>())
    var stateChanging by mutableStateOf(false)
    var stateConfirmCancel by mutableStateOf(false)
    var stateConfirmSave by mutableStateOf(false)

    fun toggleUpdating() {
        stateUpdating = !stateUpdating
    }
    fun toggleAdding() {
        stateAdding = !stateAdding
    }
    fun toggle() {
        stateUpdating = !stateUpdating
        stateAdding = !stateAdding
    }
    fun toggleSaving() {
        stateConfirmSave = !stateConfirmSave
    }
    fun toggleCancellation() {
        stateConfirmCancel = !stateConfirmCancel
    }

    fun fetchCetificate(idTutor:Int) {
        viewModelScope.launch(handler) {
            val result = profileRepository.getCertificates(idTutor)
            val listResult = mutableListOf<String?>(result.img1, result.img2, result.img3, result.img4, result.img5)
            certificates = listResult
        }
    }

    fun clearCertificate() {
        toggleUpdating()
        if(stateChanging) {
            certificates = mutableListOf<String?>()
            fetchCetificate( 1234)
            stateChanging = false
        }
    }

    fun addCertificate(certificate:String) {
        certificates.forEachIndexed {
            index: Int, s: String? ->
            if (s=="") {
                certificates[index] = certificate
                Log.d("file path: ", certificates.toString())
                stateChanging = true
                return
            }
        }
    }

    fun deleteCertificate(index:Int) {
        val newCertificates = mutableListOf<String?>()
        newCertificates.add(certificates[0])
        newCertificates.add(certificates[1])
        newCertificates.add(certificates[2])
        newCertificates.add(certificates[3])
        newCertificates.add(certificates[4])
        newCertificates[index] = ""
        val size = newCertificates.size - 1
        if(index != size) {
            for(i in index..(size-1)) {
                newCertificates[i] = newCertificates[i+1]
            }
            newCertificates[size] = ""
        }
        stateChanging = true
        certificates = newCertificates
        Log.d("new certificate: ", certificates.toString())
    }

    fun putCertificate(idTutor: Int) {
        val certificates = Certificates(idTutor, certificates[0], certificates[1], certificates[2], certificates[3], certificates[4])
        viewModelScope.launch(handler) {
            profileRepository.putCertificates(idTutor, certificates)
            stateChanging = false
            toggleUpdating()
        }
    }
}