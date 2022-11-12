package com.intern.evtutors.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.model_json.CertificateJson
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.repositories.AppInfoRepository
import com.intern.evtutors.data.repositories.CustomerRepository
import com.intern.evtutors.data.repositories.LessonRepository
import com.intern.evtutors.data.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.security.cert.Certificate
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val customerRepository: CustomerRepository
):BaseViewModel() {
    var stateUpdating by mutableStateOf(false)
    var stateAdding by mutableStateOf(false)
    var stateChanging by mutableStateOf(false)
    var stateConfirmCancel by mutableStateOf(false)
    var stateConfirmSave by mutableStateOf(false)

    var certificates by mutableStateOf(mutableListOf<String?>())
//    New formatting about certificates
    var userCertificates by mutableStateOf(mutableListOf<CertificateJson>())
    var currentCertificate  by mutableStateOf<CertificateJson?>(null)

    fun getUserCertificates() {
//        val result =
        val listResult = mutableListOf<CertificateJson>(
            CertificateJson(
                1,
                "Toeic",
                "IIG Danang",
                "11/11/2022",
                "https://res.cloudinary.com/dufcxfczn/image/upload/v1668002449/87f41f44849fb91c85d4be49f894990d_knp9j3.jpg",
                "https://res.cloudinary.com/dufcxfczn/image/upload/v1668002449/87f41f44849fb91c85d4be49f894990d_knp9j3.jpg",
                "",
                ""
            ),
            CertificateJson(
                2,
                "Ielts",
                "Hoi dong Anh",
                "11/12/2021",
                "https://res.cloudinary.com/dufcxfczn/image/upload/v1668164414/vsun2rzorzlnalsd0jzs.jpg",
                "https://res.cloudinary.com/dufcxfczn/image/upload/v1668164414/vsun2rzorzlnalsd0jzs.jpg",
                "",
                ""
            )
        )

        userCertificates = listResult
    }
    fun setCurrentCertificate(id:Int) {
        var certificate = CertificateJson(id, "Toeic", "IIG VN", "22/11/2021",
            "", "", "", "")
    }
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

    var localUser by mutableStateOf<CustomerEntity?>(null)
    fun getuser(){
        parentJob = viewModelScope.launch(handler){
            val result = customerRepository.getcustomer()
            if(result.name?.length !=0){
                localUser = result
            }
        }
    }

    fun clearCertificate(idTutor:Int) {
        toggleUpdating()
//        if(stateChanging) {
//            certificates = mutableListOf<String?>()
//            fetchCetificate( idTutor)
//            stateChanging = false
//        }
    }

    fun addCertificate(certificate:String) {
        val newCertificates = mutableListOf<String?>()
        newCertificates.add(certificates[0])
        newCertificates.add(certificates[1])
        newCertificates.add(certificates[2])
        newCertificates.add(certificates[3])
        newCertificates.add(certificates[4])
//        newCertificates.forEachIndexed {
//            index: Int, s: String? ->
//            if (s=="") {
//                newCertificates[index] = certificate
//                stateChanging = true
//                certificates = newCertificates
//                Log.d("file path: ", certificates.toString())
//                return
//            }
//        }
        stateChanging = true
    }

    fun deleteCertificate(index:Int) {
//        val newCertificates = mutableListOf<String?>()
//        newCertificates.add(certificates[0])
//        newCertificates.add(certificates[1])
//        newCertificates.add(certificates[2])
//        newCertificates.add(certificates[3])
//        newCertificates.add(certificates[4])
//        newCertificates[index] = ""
//        val size = newCertificates.size - 1
//        if(index != size) {
//            for(i in index..(size-1)) {
//                newCertificates[i] = newCertificates[i+1]
//            }
//            newCertificates[size] = ""
//        }
//        stateChanging = true
//        certificates = newCertificates
//        Log.d("new certificate: ", certificates.toString())
    }

    fun putCertificate(idTutor: Int) {
//        val newCertificates = Certificates(idTutor, certificates[0], certificates[1], certificates[2], certificates[3], certificates[4])
//        viewModelScope.launch(handler) {
//            profileRepository.putCertificates(idTutor, newCertificates)
//            stateChanging = false
//            toggleUpdating()
//        }
        stateChanging = false
        toggleUpdating()
    }
}