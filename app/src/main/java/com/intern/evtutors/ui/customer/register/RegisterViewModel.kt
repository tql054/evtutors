package com.intern.evtutors.ui.customer.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.models.Role
import com.intern.evtutors.data.models.User

import com.intern.evtutors.data.repositories.jsonRegisterStudentReposititory
import com.intern.evtutors.data.repositories.jsonRegisterTeacherReposititory
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val jsonRegisterTeacherReposititory: jsonRegisterTeacherReposititory,
                                            private val jsonRegisterStudentReposititory: jsonRegisterStudentReposititory)
    : BaseViewModel() {

    private var _listPosts = MutableLiveData<UserJson>()
    val listPots: LiveData<UserJson>
        get() = _listPosts

     suspend fun fetchRegisterTeacher(name:String, pass:String, email:String):UserJson? {
         var role     : MutableSet<Role> = mutableSetOf()
        // var user: User= User(0,"",0,"","","","","","","")
        var user: User= User(0,"",0,"","","","","","","",role)
        user.name=name
        user.email=email
        user.userName=name
        user.password=pass

         val post = jsonRegisterTeacherReposititory.toRegitser(user)
         return post as UserJson?
    }

    suspend fun generateCertificates(userId:Int) {
        val certificates = Certificates(userId, "", "", "", "", "")
        Log.d("Return certifcates","")
        val result = jsonRegisterTeacherReposititory.renerateCertificates(certificates)
    }

     suspend fun fetchRegisterStudent(name:String,pass:String,email:String):UserJson? {
        showLoading(true)


         var role     : MutableSet<Role> = mutableSetOf()
       //  var user: User= User(0,"",0,"","","","","","","")
        var user: User= User(0,"",0,"","","","","","","",role)
        user.name=name
        user.email=email
        user.userName=name
        user.password=pass
         val post = jsonRegisterStudentReposititory.toRegitser(user)
         return post as UserJson?
    }

}


