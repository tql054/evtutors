package com.intern.evtutors.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intern.evtutors.data.database.entities.CustomerEntity

@Dao
interface CustomerDao {

    @Query("select * from customerUser1")
    suspend fun getUser(): CustomerEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customerEntity: CustomerEntity)

}