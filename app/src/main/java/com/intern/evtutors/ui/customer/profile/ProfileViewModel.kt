package com.intern.evtutors.ui.customer.profile

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.repositories.CustomerRepository
import com.intern.evtutors.data.repositories.JsonLoginRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
)
    : BaseViewModel() {

    var user: CustomerEntity= CustomerEntity(0,0,"",0,"","","","","","","",0)
    var user1:CustomerEntity?=null

    var myuser : CustomerEntity by mutableStateOf(user)





    fun getuser(){
        showLoading(true)

        parentJob = viewModelScope.launch  (handler){
            myuser =customerRepository.getcustomer()

        }
       // Name.value=user
        registerJobFinish()
    }
}