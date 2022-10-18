package com.intern.evtutors.data.repositories

import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.services.CustomerLocalService
import com.intern.evtutors.data.services.CustomerRemoteService
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CustomerRepository @Inject constructor(

    private val customerLocalService: CustomerLocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun creatercustomer(customerEntity: CustomerEntity)= withContext(dispatcher){
        customerLocalService.CreateCustomer(customerEntity)
    }

    suspend fun getcustomer()= withContext(dispatcher){
        customerLocalService.getCustomer()
    }
}