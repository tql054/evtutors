package com.intern.evtutors.ui.customer.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.models.Account
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

    private fun cover(a : Int, b: String, c: Int): CustomerEntity{
        return CustomerEntity(
            constans =1,
            id = a,
            name = b,
            age = c
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
//        if(post.user.userName.length > 0){
//            var a= cover(post.user.id,post.user.userName,post.user.age)
//            create(a)
//        }
        return token
    }
    //    private var _listPosts = MutableStateFlow(emptyList<getjwtToken>())
//    val listPots: StateFlow<List<getjwtToken>>
//        get() = _listPosts
//    override fun fetchDataLogin(email:String,passWord: String) {
//        showLoading(true)
//        var account = Account()
//        account.userName=email
//        account.userPassword=passWord
//        parentJob = viewModelScope.launch(handler) {
//            val series = seri
//            val post = jsonLoginRepositories.getAllAccount(account)
//            _listPosts.postValue(post)
//
//        }
//        registerJobFinish()
//    }
}