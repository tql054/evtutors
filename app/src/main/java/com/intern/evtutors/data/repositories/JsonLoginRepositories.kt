package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.base.network.NetworkResult2
import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken
import com.intern.evtutors.data.services.ProfileServices
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class JsonLoginRepositories @Inject constructor(
    private val profileServices: ProfileServices,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    suspend fun getAllAccount(account: Account) = withContext(dispatcher){
       val data:getjwtToken?=null
        when (val result =profileServices.getAllAccount(account)){

            is NetworkResult.Success<*> ->{
              result.data
            }
            is NetworkResult.Error ->{
                null
            }
        }
    }

    suspend fun UpdateAccount(idUser: Int,user: User) = withContext(dispatcher){
        val data:User?=null
        when (val result =profileServices.UpdateAccount(idUser,user)){
            is NetworkResult.Success<*> ->{
                result.data
            }
            is NetworkResult.Error ->{
                null
            }
        }
    }
}


