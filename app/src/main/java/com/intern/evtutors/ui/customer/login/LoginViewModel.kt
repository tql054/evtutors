package com.intern.evtutors.ui.customer.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val jsonLoginRepositories: JsonLoginRepositories,
                                         private val customerRepository: CustomerRepository
): BaseViewModel() {
    private var _listPosts = MutableLiveData<getjwtToken>()
    val listPots: LiveData<getjwtToken> get() = _listPosts
    var user by mutableStateOf(CustomerEntity(0,0,"",0))


    fun create(customerEntity: CustomerEntity){
        parentJob = viewModelScope.launch  (handler){
            customerRepository.creatercustomer(customerEntity)
        }
    }
    fun getuser(){
        parentJob = viewModelScope.launch(handler){
            val result = customerRepository.getcustomer()
            if(result.name?.length !=0){
                user = result
            }
        }
    }

    private fun cover(a : Int, b: String, c: Int): CustomerEntity{
        return CustomerEntity(
            constans =1,
            id = a,
            name = b,
            age = c
        )

    }

    override fun fetchDataLogin(email:String,passWord: String) {
        showLoading(true)
        var account = Account()
        account.userName=email
        account.userPassword=passWord
        parentJob = viewModelScope.launch(handler) {
            val post = jsonLoginRepositories.getAllAccount(account)
            _listPosts.postValue(post)
            if(post.user.userName.length > 0){
                var a= cover(post.user.id,post.user.userName,post.user.age)
                create(a)
            }


        }
        registerJobFinish()
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