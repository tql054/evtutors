package com.intern.evtutors.data.services

import com.intern.evtutors.data.database.daos.CustomerDao
import javax.inject.Inject

class CustomerLocalService @Inject constructor(private val customerDao: CustomerDao) {
}