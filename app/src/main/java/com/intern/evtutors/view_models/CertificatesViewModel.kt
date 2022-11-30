package com.intern.evtutors.view_models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.intern.evtutors.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CertificatesViewModel @Inject constructor(

):BaseViewModel()  {
    var certificates by mutableStateOf(mutableListOf<String>())
    var stateUpload by mutableStateOf(false)
    var stateValidData by mutableStateOf(false)
    var stateErrorBox by mutableStateOf(false)
    var stateLoadingImage by mutableStateOf(false)

    fun addImageCertificate(url:String) {
        if (certificates.size < 5) {
            val newCertificates = mutableListOf<String>()
            for (certificate in certificates) newCertificates.add(certificate)
            newCertificates.add(url)
            certificates = newCertificates
        }
        stateUpload = false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkValidDate (dateBegin:String, dateEnd:String):Boolean {
        return if(dateBegin == "Date of issue" || dateEnd == "Date of expiration") {
            stateValidData = false
            false
        } else {
            val now = LocalDate.now()
            var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            var localDateBegin = LocalDate.parse(dateBegin, formatter)
            var localDateEnd = LocalDate.parse(dateEnd, formatter)
            localDateEnd.isBefore(localDateBegin) && now.isBefore(localDateBegin)
        }
    }

}