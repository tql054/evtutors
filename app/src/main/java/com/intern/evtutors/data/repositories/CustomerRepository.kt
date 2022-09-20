package com.intern.evtutors.data.repositories

import com.intern.evtutors.data.services.CustomerLocalService
import com.intern.evtutors.data.services.CustomerRemoteService
import com.intern.evtutors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher

class CustomerRepository constructor(
    private val customerRemoteService: CustomerRemoteService,
    private val customerLocalService: CustomerLocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
}