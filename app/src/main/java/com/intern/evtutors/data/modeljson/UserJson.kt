package com.intern.evtutors.data.modeljson


import com.intern.evtutors.data.models.User

class UserJson(
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


    )
{
    fun toUser(): User {
        return User(
            id=id,
            name=name,
            age=age,
            gender=gender,
            address=address,
            phone=phone,
            avatar=avatar,
            email=email,
            userName=userName,
            password=password,

            )
    }
}