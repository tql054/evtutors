package com.intern.evtutors.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.model_json.CertificateJson
import com.intern.evtutors.data.repositories.CustomerRepository
import com.intern.evtutors.data.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val customerRepository: CustomerRepository
):BaseViewModel() {
    var stateInitialLoading by mutableStateOf(true)
    var stateUpdating by mutableStateOf(false)
    var stateAdding by mutableStateOf(false)
    var stateChanging by mutableStateOf(false)
    var stateConfirmCancel by mutableStateOf(false)
    var stateConfirmSave by mutableStateOf(false)
    var stateLoadingSaving by mutableStateOf(false)
    var localUser by mutableStateOf<CustomerEntity>(
        CustomerEntity(0,0,"",0,"","","","","","","",0)
    )
    var oldCertificates by mutableStateOf(mutableListOf<CertificateJson>())
    var userCertificates by mutableStateOf(mutableListOf<CertificateJson>())
    var currentCertificate  by mutableStateOf<CertificateJson?>(null)

//    var user: CustomerEntity= CustomerEntity(0,0,"",0,"","","","","","","",0)
//    var myuser : CustomerEntity by mutableStateOf(user)
//
//    fun getuser(){
//        parentJob = viewModelScope.launch  (handler){
//            myuser =customerRepository.getcustomer()
//
//        }
//    }
    fun toggleUpdating() {
        stateUpdating = !stateUpdating
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

    fun getUserCertificates(idTutor:Int) {
        viewModelScope.launch(handler) {
            val results = profileRepository.getCertificates(idTutor)
            oldCertificates = results!!
            userCertificates = results!!
            stateInitialLoading = false
        }
    }
    fun setCurrentCertificate(id:Int) {
        for (certificate in userCertificates) {
            if(certificate.id == id) currentCertificate = certificate
        }
    }

    fun getUser(){
        parentJob = viewModelScope.launch(handler){
            val result = customerRepository.getcustomer()
            if(result.name?.length !=0){
                localUser = result
            }
        }
    }

    fun clearCertificate(idTutor:Int) {
        stateInitialLoading = true
        toggleUpdating()
        if(stateChanging) {
            userCertificates = mutableListOf<CertificateJson>()
            getUserCertificates( idTutor)
            stateChanging = false
        }
    }

    fun addCertificate(certificateJson: CertificateJson) {
        Log.d("Added certificate",certificateJson.name)
        if(userCertificates.size < 5) {
            val newCertificates = mutableListOf<CertificateJson>()
            for (certificate in userCertificates) {
                newCertificates.add(certificate)
            }
            newCertificates.add(certificateJson)
            userCertificates = newCertificates
        }
        stateChanging = true
    }

    fun deleteCertificate(id:Int) {
        val newCertificates = mutableListOf<CertificateJson>()
        var indexDeleted = 10
        userCertificates.forEachIndexed { index, certificate ->
            newCertificates.add(certificate)
            if (certificate.id ==  id) {
                indexDeleted = index
            }
        }
        if(indexDeleted<4) {
            newCertificates.removeAt(indexDeleted)
            userCertificates = newCertificates
            stateChanging = true
            stateUpdating = true
        }

        Log.d("new certificate: ", userCertificates.size.toString())
    }

    fun putCertificate() {
        val deletedList = findDeletedCertificate(userCertificates)
        val addedList = findAddedCertificate(userCertificates)
        viewModelScope.launch(handler) {
            deletedList.forEach {
                profileRepository.deleteCertificates(it.id)
                Log.d("deleted Certificate: ", it.id.toString())
            }
            addedList.forEach {
                profileRepository.putCertificates(localUser!!.id, it)
            }
            Log.d("deleted Certificate: ", deletedList.size.toString())
            Log.d("added Certificate: ", addedList.size.toString())
            stateChanging = false
            stateLoadingSaving = false
            toggleUpdating()
            getUserCertificates(localUser!!.id)
        }
    }

    private fun findDeletedCertificate(newList:MutableList<CertificateJson>):MutableList<CertificateJson> {
        val result = mutableListOf<CertificateJson>()
        for(certificate in oldCertificates) {
            if(!newList.contains(certificate)) {
                result.add(certificate)
            }
        }
        return result
    }

    private fun findAddedCertificate(newList:MutableList<CertificateJson>):MutableList<CertificateJson> {
        val result = mutableListOf<CertificateJson>()
        for(certificate in newList) {
            if(!oldCertificates.contains(certificate)) {
                result.add(certificate)
            }
        }
        return result
    }
}