package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.base.network.NetworkResult2
import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken
import com.intern.evtutors.data.services.JsonLoginServer
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class JsonLoginRepositories @Inject constructor(
    private val jsonLessonRemoteServer: JsonLoginServer,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){


        suspend fun getAllAccount(account: Account) = withContext(dispatcher){
           val data:getjwtToken?=null
            when (val result =jsonLessonRemoteServer.getAllAccount(account)){

                is NetworkResult2.Success<*> ->{

                  result.data
                }
                is NetworkResult2.Error ->{
                    data
                }
                NetworkResult2.Loading->{

                }
            }
        }

    suspend fun UpdateAccount(idUser: Int,user: User) = withContext(dispatcher){
        val data:User?=null
        when (val result =jsonLessonRemoteServer.UpdateAccount(idUser,user)){

            is NetworkResult2.Success<*> ->{

                result.data
            }
            is NetworkResult2.Error ->{
                data
            }
            NetworkResult2.Loading->{

            }
        }
    }
    }


