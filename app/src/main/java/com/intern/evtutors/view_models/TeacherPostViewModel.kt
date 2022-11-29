package com.intern.evtutors.view_models

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.composes.teacherPostInfo.ui.ItemHoursShifts
import com.intern.evtutors.data.models.TeacherPost
import com.intern.evtutors.data.models.TimePost
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TeacherPostViewModel @Inject constructor(

): BaseViewModel()  {
    var timeMonday by mutableStateOf(mutableListOf<TimePost>())
    var timeTuesday by mutableStateOf(mutableListOf<TimePost>())
    var timeWednesday by mutableStateOf(mutableListOf<TimePost>())
    var timeThursday by mutableStateOf(mutableListOf<TimePost>())
    var timeFriday by mutableStateOf(mutableListOf<TimePost>())
    var timeSaturday by mutableStateOf(mutableListOf<TimePost>())
    var timeSunday by mutableStateOf(mutableListOf<TimePost>())

    var timePost= TimePost(1,"","","")
    var time: MutableSet<TimePost> = mutableSetOf(timePost)

    var Numberphone by mutableStateOf("")
    var NameCity by mutableStateOf("")

    var NameDistrict by mutableStateOf("")
    var NameAdress by mutableStateOf("")






    fun addtime(i:String,inputTime:TimePost,nameday:String) {

            //val newTime = mutableListOf(TimePost(timeMonday.size+1,null,null))
        val newTime = mutableListOf <TimePost>()
        val newItemList = mutableListOf <String>()
            when (nameday){
                "Monday"-> {
                    if (timeMonday.size < 4) {
                        for (time in timeMonday) newTime.add(time)
                        newTime.add(inputTime)
                        timeMonday = newTime
                    }

                }
                "Tuesday"-> {
                    if (timeTuesday.size < 4) {
                        for (time in timeTuesday) newTime.add(time)
                        newTime.add(inputTime)
                        timeTuesday = newTime
                    }

                }
                "Wednesday"-> {
                    if (timeWednesday.size < 4) {
                        for (time in timeWednesday) newTime.add(time)
                        newTime.add(inputTime)
                        timeWednesday = newTime
                    }

                }
                "Thursday"-> {
                    if (timeThursday.size < 4) {
                        for (time in timeThursday) newTime.add(time)
                        newTime.add(inputTime)
                        timeThursday = newTime
                    }

                }
                "Friday"-> {
                    if (timeFriday.size < 4) {
                        for (time in timeFriday) newTime.add(time)
                        newTime.add(inputTime)
                        timeFriday = newTime
                    }

                }
                "Saturday"-> {
                    if (timeSaturday.size < 4) {
                        for (time in timeSaturday) newTime.add(time)
                        newTime.add(inputTime)
                        timeSaturday = newTime
                    }

                }
                "Sunday"-> {
                    if (timeSunday.size < 4) {
                        for (time in timeSunday) newTime.add(time)
                        newTime.add(inputTime)
                        timeSunday = newTime
                    }
                }
            }

    }

//    fun deletetime(id:Int,nameday: String) {
//        val newTime = mutableListOf<TimePost>()
//        var indexDeleted = 5
//
//        when(nameday){
//            "Monday"->{
//                timeMonday.forEachIndexed { index, time ->
//                    newTime.add(time)
//                    if (time.id ==  id) {
//                        indexDeleted = index
//                    }
//                }
//                if(indexDeleted<5) {
//                    newTime.removeAt(indexDeleted-1)
//                    timeMonday = newTime
//
//                }
//            }
//            "Tuesday"->{
//                timeTuesday.forEachIndexed { index, time ->
//                    newTime.add(time)
//                    if (time.id ==  id) {
//                        indexDeleted = index
//                    }
//                }
//                if(indexDeleted<5) {
//                    newTime.removeAt(indexDeleted)
//                    timeTuesday = newTime
//
//                }
//            }
//            "Wednesday"->{
//                timeWednesday.forEachIndexed { index, time ->
//                    newTime.add(time)
//                    if (time.id ==  id) {
//                        indexDeleted = index
//                    }
//                }
//                if(indexDeleted<5) {
//                    newTime.removeAt(indexDeleted)
//                    timeWednesday = newTime
//
//                }
//            }
//            "Friday"->{
//                timeFriday.forEachIndexed { index, time ->
//                    newTime.add(time)
//                    if (time.id ==  id) {
//                        indexDeleted = index
//                    }
//                }
//                if(indexDeleted<5) {
//                    newTime.removeAt(indexDeleted)
//                    timeFriday = newTime
//
//                }
//            }
//            "Saturday"->{
//                timeSaturday.forEachIndexed { index, time ->
//                    newTime.add(time)
//                    if (time.id ==  id) {
//                        indexDeleted = index
//                    }
//                }
//                if(indexDeleted<5) {
//                    newTime.removeAt(indexDeleted)
//                    timeSaturday = newTime
//
//                }
//            }
//            "Sunday"->{
//                timeSunday.forEachIndexed { index, time ->
//                    newTime.add(time)
//                    if (time.id ==  id) {
//                        indexDeleted = index
//                    }
//                }
//                if(indexDeleted<5) {
//                    newTime.removeAt(indexDeleted)
//                    timeSunday = newTime
//
//                }
//            }
//            "Thursday"->{
//                timeThursday.forEachIndexed { index, time ->
//                    newTime.add(time)
//                    if (time.id ==  id) {
//                        indexDeleted = index
//                    }
//                }
//                if(indexDeleted<5) {
//                    newTime.removeAt(indexDeleted)
//                    timeThursday = newTime
//
//                }
//            }
//        }
//
//
//
//    }

    fun deletetime(id:Int,nameday: String) {
        val newTime = mutableListOf<TimePost>()

        var indexDeleted = 5


        when(nameday){
            "Monday"->{
                timeMonday.forEachIndexed { index, time ->
                    newTime.add(time)
                    if (time.id ==  id) {
                        indexDeleted = index
                    }
                }
                if(indexDeleted!=5) {
                    newTime.removeAt(indexDeleted)
                    timeMonday = newTime

                }


            }
            "Tuesday"->{
                timeTuesday.forEachIndexed { index, time ->
                    newTime.add(time)
                    if (time.id ==  id) {
                        indexDeleted = index
                    }
                }
                if(indexDeleted<5) {
                    newTime.removeAt(indexDeleted)
                    timeTuesday = newTime

                }
            }

        }



    }

    fun updateTime(nameday: String  ,timePost: TimePost){
        when(nameday){
            "Monday"->{
                timeMonday.forEach {
                    if(it.id==timePost.id){
                        it.TimeEnd=timePost.TimeEnd
                        it.TimeStrart=timePost.TimeStrart
                    }
                }
            }
            "Tuesday"-> {
                timeTuesday.forEach {

                    if(it.id==timePost.id){
                        Log.d(TAG,"id update"+it.id)
                        it.TimeEnd=timePost.TimeEnd
                        it.TimeStrart=timePost.TimeStrart
                    }
                }
            }
        }
    }
}