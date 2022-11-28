package com.intern.evtutors.data.models

data class TeacherPost(

    var Subjects: String,
    var Form:String,
    var ShiftsCount: Int,
    var Schedule: MutableSet<TimePost>,
    var Startday:String,
    var Phone: String,
    var Adress: String,
    var TutorName: String,
)
