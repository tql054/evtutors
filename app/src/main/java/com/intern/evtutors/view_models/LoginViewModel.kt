package com.intern.evtutors.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.model_json.TeacherDegree
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
    var dataUserLogin by mutableStateOf<getjwtToken?>(null)
    private var _listPosts = MutableLiveData<getjwtToken>()
    val listPots: LiveData<getjwtToken> get() = _listPosts
    var teacherDegree by mutableStateOf(mutableListOf<TeacherDegree?>())

    fun create(customerEntity: CustomerEntity){
        parentJob = viewModelScope.launch  (handler){
            customerRepository.creatercustomer(customerEntity)
        }
    }

     fun cover( user: getjwtToken): CustomerEntity{
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

    suspend fun DataLogin(email:String,passWord: String) {
        var account = Account()
        account.userName=email
        account.userPassword=passWord
            dataUserLogin  = jsonLoginRepositories.getAllAccount(account) as getjwtToken?
            if(dataUserLogin !=null){
                var a= cover(dataUserLogin!!)
                create(a)
            }
    }

    suspend fun UpdateAccount(idUser: Int, user: User) {
            myuserupdate = jsonLoginRepositories.UpdateAccount(idUser,user) as User
    }
    suspend fun getDegreeTutor(id: Int) {
        teacherDegree = jsonLoginRepositories.getDegreeTutor(id) as MutableList<TeacherDegree?>
    }
}