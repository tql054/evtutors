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
import com.intern.evtutors.data.models.Role
import com.intern.evtutors.data.models.User
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
)
    : BaseViewModel() {
    var role: MutableSet<Role> = mutableSetOf()
    var user: User= User(0,"",0,"","","","","","","",role)
    var myuserupdate : User by mutableStateOf(user)
    private var _listPosts = MutableLiveData<getjwtToken>()
    val listPots: LiveData<getjwtToken> get() = _listPosts
    var localUser by mutableStateOf<CustomerEntity?>(null)
    fun create(customerEntity: CustomerEntity){
        parentJob = viewModelScope.launch  (handler){
            customerRepository.creatercustomer(customerEntity)
        }
    }
    fun getuser(){
        parentJob = viewModelScope.launch(handler){
            val result = customerRepository.getcustomer()
            if(result.name?.length !=0){
                localUser = result
            }
        }
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

    fun UpdateAccount(idUser: Int,user: User) {
        parentJob = viewModelScope.launch  (handler){
            myuserupdate = jsonLoginRepositories.UpdateAccount(idUser,user) as User

        }
    }

}