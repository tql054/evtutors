package com.intern.evtutors.ui.customer.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.modeljson.UserJson
import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken
import com.intern.evtutors.data.repositories.JsonLoginRepositories
import com.intern.evtutors.data.repositories.jsonRegisterStudentReposititory
import com.intern.evtutors.data.repositories.jsonRegisterTeacherReposititory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val jsonRegisterTeacherReposititory: jsonRegisterTeacherReposititory,
                                            private val jsonRegisterStudentReposititory: jsonRegisterStudentReposititory)
    : BaseViewModel() {

    private var _listPosts = MutableLiveData<UserJson>()
    val listPots: LiveData<UserJson>
        get() = _listPosts

     fun fetchRegisterTeacher(name:String,pass:String,email:String) {
        showLoading(true)
        var user: User= User(0,"",0,"","","","","","","")
        user.name=name
        user.email=email
        user.userName=name
        user.password=pass
        parentJob = viewModelScope.launch(handler) {
            val post = jsonRegisterTeacherReposititory.toRegitser(user)
            _listPosts.postValue(post)
            val user =post

        }
      //  registerJobFinish()
    }
     suspend fun fetchRegisterStudent(name:String,pass:String,email:String) {
        showLoading(true)



        var user: User= User(0,"",0,"","","","","","","")
        user.name=name
        user.email=email
        user.userName=name
        user.password=pass

        parentJob = viewModelScope.launch(handler) {
            val post = jsonRegisterStudentReposititory.toRegitser(user)
            _listPosts.postValue(post)

        }
        registerJobFinish()
    }

}