package com.intern.evtutors.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "customerUser1")
data class CustomerEntity (@PrimaryKey
                           val constans:Int,
                           var id: Int,
                           var name:String,
                           var age: Int,
                           var gender: String,
                           var address: String,
                           var phone: String,
                           var avatar: String,
                           var email: String,
                           var userName:String,
                           var password: String,
                           var roleID:Int)