package com.intern.evtutors.data.services

import com.intern.evtutors.data.apis.CustomerAPI
import javax.inject.Inject

class CustomerRemoteService @Inject constructor(private val customerAPI: CustomerAPI) {
}