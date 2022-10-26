package com.intern.evtutors.ui.customer.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken
import com.intern.evtutors.data.repositories.CustomerRepository
import com.intern.evtutors.data.repositories.JsonLoginRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val jsonLoginRepositories: JsonLoginRepositories,
                                         private val customerRepository: CustomerRepository
)
    : BaseViewModel() {



    private var _listPosts = MutableLiveData<getjwtToken>()
    val listPots: LiveData<getjwtToken> get() = _listPosts

    fun create(customerEntity: CustomerEntity){
        showLoading(true)
        parentJob = viewModelScope.launch  (handler){
            customerRepository.creatercustomer(customerEntity)
        }
        registerJobFinish()
    }
    fun getuser():Boolean{
        showLoading(true)
        parentJob = viewModelScope.launch  (handler){
            if(customerRepository.getcustomer().name?.length ==null){

            }
        }
        return true
        registerJobFinish()
    }

    private fun cover( user: getjwtToken): CustomerEntity{
        var ID :Int =0
        for (i in user.user.role){
             ID = i.id!!
        }

        return CustomerEntity(
            constans =1,
            id=user.user.id,
            name=user.user.name,
            age= user.user.age,
            gender= user.user.gender,
            address= user.user.address,
            phone= user.user.phone,
            avatar=user.user.avatar,
            email= user.user.email,
            userName=user.user.userName,
            password= user.user.password,
            roleID = ID ,
        )

    }

    suspend fun DataLogin(email:String,passWord: String):getjwtToken? {
        var token:getjwtToken? = null
        var account = Account()
        account.userName=email
        account.userPassword=passWord
        val post = jsonLoginRepositories.getAllAccount(account)
        token = post as getjwtToken?
//        _listPosts.postValue(post)
        if(post !=null){
            var a= cover(post)
            create(a)
        }
        return token
    }

}