package com.intern.evtutors.ui.customer.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.models.Role
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.repositories.RegisterRepositories

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepositories: RegisterRepositories)
    : BaseViewModel() {
    var role: MutableSet<Role> = mutableSetOf()
    private var _listPosts = MutableLiveData<UserJson>()
    val listPots: LiveData<UserJson>
        get() = _listPosts

    suspend fun registerTeacher(name:String, pass:String, email:String):UserJson? {
        var user: User= User(0,"",0,"","","","","","","",role)
        user.name=name
        user.email=email
        user.userName=name
        user.password=pass

         val post = registerRepositories.registerTeacher(user)
         return post as UserJson?
    }

    suspend fun generateCertificates(userId:Int) {
        val certificates = Certificates(userId, "", "", "", "", "")
        val result = registerRepositories.generateCertificates(certificates)
    }

     suspend fun registerStudent(name:String,pass:String,email:String):UserJson? {
        var role     : MutableSet<Role> = mutableSetOf()
        var user: User= User(0,"",0,"","","","","","","",role)
        user.name=name
        user.email=email
        user.userName=name
        user.password=pass
         val post = registerRepositories.registerStudent(user)
         return post as UserJson?
    }

}


