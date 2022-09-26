package com.intern.evtutors.data.repositories

import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.models.Account
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
            when (val result =jsonLessonRemoteServer.getAllAccount(account)){

                is NetworkResult.Success ->{

                  result.data
                }
                is NetworkResult.Error ->{
                    throw result.exception
                }
            }
        }
    }


