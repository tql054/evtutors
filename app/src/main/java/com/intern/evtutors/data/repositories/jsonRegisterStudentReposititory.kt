package com.intern.evtutors.data.repositories


import com.intern.evtutors.base.network.NetworkResult
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.services.RegisterStudentRemoteServer
import com.intern.evtutors.data.services.RegisterTeacherRemoteServer
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class jsonRegisterStudentReposititory @Inject constructor(
    private val registerTeacherRemoteServer: RegisterStudentRemoteServer,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){

    suspend fun toRegitser(user: User) = withContext(dispatcher){
        when (val result =registerTeacherRemoteServer.todoRegister(user)){

            is NetworkResult.Success ->{

                result.data
                //true
            }
            is NetworkResult.Error ->{
                throw result.exception
            }
        }
    }
}
