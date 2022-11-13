package com.intern.evtutors.view_models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.intern.evtutors.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CertificatesViewModel @Inject constructor(

):BaseViewModel()  {
    var certificates by mutableStateOf(mutableListOf<String>())
    var stateUpload by mutableStateOf(false)
    var stateValidData by mutableStateOf<Boolean>(false)
    var stateErrorBox by mutableStateOf<Boolean>(false)

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
        if(dateBegin == "Date of issue" || dateEnd == "Date of expiration") {
            stateValidData = false
            return false
        } else {
            val now = LocalDate.now()
            var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            var localDateBegin = LocalDate.parse(dateBegin, formatter)
            var localDateEnd = LocalDate.parse(dateEnd, formatter)
            return localDateEnd.isBefore(localDateBegin) && now.isBefore(localDateBegin)
        }
    }

}