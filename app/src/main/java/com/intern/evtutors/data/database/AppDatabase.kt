package com.intern.evtutors.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.intern.evtutors.data.database.daos.CustomerDao
import com.intern.evtutors.data.database.entities.CustomerEntity

@Database(entities = [CustomerEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun customerDao(): CustomerDao
}