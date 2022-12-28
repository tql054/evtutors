package com.intern.evtutors.view_models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.activities.view
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.data.repositories.LessonRepository
import com.intern.evtutors.data.services.LessonServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
):BaseViewModel() {
    var stateDateOfWeek by mutableStateOf(mutableListOf<DateItem>())
    var stateDateOfNextWeek by mutableStateOf(mutableListOf<DateItem>())
    var stateCurrentDate by mutableStateOf(LocalDate.now())
    var stateListLesson by mutableStateOf(mutableListOf<Lesson>())
    var stateCurrentWeek by mutableStateOf(true)
    var plusDaysToNextWeek by mutableStateOf(7)
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTeacherLessonByDate() {
        viewModelScope.launch(handler) {
            var listLesson = mutableListOf<Lesson>()
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val localDate = stateCurrentDate.format(formatter)
            //need to handle network exception
            val result = lessonRepository.getAllLessonByDate("Teacher", 7, localDate)
            result.map {
                listLesson.add(it)
            }
            Log.d("Date formatted:", localDate.toString())
            stateListLesson = listLesson
        }
    }

    fun changeWeek(weekValue:String) {
        val localDate = LocalDate.now()
        when(weekValue) {
            "this week" -> {
                stateCurrentWeek = true
                activeDateOfWeek(localDate.toString())
            }

            "next week" -> {
                stateCurrentWeek = false
                val dayOfNextWeek = localDate.plusDays(plusDaysToNextWeek.toLong())
                activeDateOfNextWeek(dayOfNextWeek.toString())
            }
        }
    }

    fun activeDateOfWeek(activatedDate:String) {
        var newList =  mutableListOf<DateItem>()
        for(date in stateDateOfWeek) {
            if(activatedDate == date.date.toString()) {
                stateCurrentDate = date.date
                newList.add(DateItem(date.date, date.current, true))
            }
            else
                newList.add(DateItem(date.date, date.current, false))
        }
        stateDateOfWeek = newList
    }

    fun activeDateOfNextWeek(activatedDate:String) {
        var newList =  mutableListOf<DateItem>()
        for(date in stateDateOfNextWeek) {
            if(activatedDate == date.date.toString()) {
                stateCurrentDate = date.date
                newList.add(DateItem(date.date, date.current, true))
            }
            else
                newList.add(DateItem(date.date, date.current, false))
        }
        stateDateOfNextWeek = newList
    }

    fun getListDayOfNextWeek() {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListDayOfWeek(
    ) {
        var plusDays = 6
        var minusDays = 0
        val localDate = LocalDate.now()
        when (localDate.dayOfWeek.name) {
            "TUESDAY" -> {
                plusDays = 5
                minusDays = 1
                plusDaysToNextWeek = 6
            }
            "WEDNESDAY" -> {
                plusDays = 4
                minusDays = 2
                plusDaysToNextWeek = 5
            }
            "THURSDAY" -> {
                plusDays = 3
                minusDays = 3
                plusDaysToNextWeek = 4
            }
            "FRIDAY" -> {
                plusDays = 2
                minusDays = 4
                plusDaysToNextWeek = 3
            }
            "SATURDAY" -> {
                plusDays = 1
                minusDays = 5
                plusDaysToNextWeek = 2
            }
            "SUNDAY" -> {
                plusDays = 0
                minusDays = 6
                plusDaysToNextWeek = 1
            }
        }
        getListDayByLocalDate(localDate, plusDays, minusDays)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListDayByLocalDate(
        localDate: LocalDate,
        plusDays:Int,
        minusDays:Int
    ){
        val result = mutableListOf<DateItem>()
        val resultNextWeek = mutableListOf<DateItem>()
        for(index in -minusDays..-1) {
            val prevDate = localDate.plusDays(index.toLong())
            result.add(DateItem(prevDate, current = false, active = false))
        }
        result.add(DateItem(localDate, current = true, active = true))
        for(index in 1..plusDays) {
            val nextDate = localDate.plusDays(index.toLong())
            result.add(DateItem(nextDate, current = false, active = false))
        }

        for(index in (plusDays+1)..(plusDays+7)) {
            val nextDate = localDate.plusDays(index.toLong())
            resultNextWeek.add(DateItem(nextDate, current = false, active = false))
        }
        stateDateOfWeek =  result
        stateDateOfNextWeek = resultNextWeek
        Log.d("state next week:", stateDateOfNextWeek.size.toString())
        Log.d("state current week:", stateDateOfWeek.size.toString())

    }

    data class DateItem(
        val date: LocalDate,
        val current:Boolean,
        val active:Boolean
    )


}