package com.intern.evtutors.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "customerUser")
data class CustomerEntity (@PrimaryKey
                           val constans:Int,
                           val id: Int,
                           val name: String?,
                           val age: Int?,)