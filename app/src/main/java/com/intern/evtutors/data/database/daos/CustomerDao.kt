package com.intern.evtutors.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.intern.evtutors.data.database.entities.CustomerEntity

@Dao
interface CustomerDao {

    @Query("select * from customer")
    fun getAll(): List<CustomerEntity>

}