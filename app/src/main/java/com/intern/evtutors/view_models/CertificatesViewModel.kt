package com.intern.evtutors.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.intern.evtutors.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CertificatesViewModel @Inject constructor(

):BaseViewModel()  {
    var certificates by mutableStateOf(mutableListOf<String>())
    var stateName by mutableStateOf<String>("")
    var stateAddress by mutableStateOf<String>("")
    var stateDOI by mutableStateOf<String>("") // DOI: Date of issue
    var stateDOE by mutableStateOf<String>("") // DOI: Date of issue
    var stateUpload by mutableStateOf(false)
    var stateValidateName by mutableStateOf(true)
    var stateValidateAddress by mutableStateOf(true)

    fun addImageCertificate(url:String) {
        if (certificates.size < 5) {
            val newCertificates = mutableListOf<String>()
            for (certificate in certificates) newCertificates.add(certificate)
            newCertificates.add(url)
            certificates = newCertificates
        }
        stateUpload = false
    }
}