package com.intern.evtutors.view_models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.intern.evtutors.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor():BaseViewModel() {
    var stateDateOfWeek by mutableStateOf(mutableListOf<DateItem>())

    fun activeDateOfWeek(activatedDate:String) {
        var newList =  mutableListOf<DateItem>()
        for(date in stateDateOfWeek) {
            if(activatedDate == date.date.toString())
                newList.add(DateItem(date.date, date.current, true))
            else
                newList.add(DateItem(date.date, date.current, false))
        }
        stateDateOfWeek = newList
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
            }
            "WEDNESDAY" -> {
                plusDays = 4
                minusDays = 2
            }
            "THURSDAY" -> {
                plusDays = 3
                minusDays = 3
            }
            "FRIDAY" -> {
                plusDays = 2
                minusDays = 3
            }
            "SATURDAY" -> {
                plusDays = 1
                minusDays = 4
            }
            "SUNDAY" -> {
                plusDays = 0
                minusDays = 6
            }
        }
        stateDateOfWeek = getListDayByLocalDate(localDate, plusDays, minusDays)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListDayByLocalDate(
        localDate: LocalDate,
        plusDays:Int,
        minusDays:Int
    ):MutableList<DateItem> {
        val result = mutableListOf<DateItem>()
        for(index in -minusDays..-1) {
            val prevDate = localDate.plusDays(index.toLong())
            result.add(DateItem(prevDate, current = false, active = false))
        }
        result.add(DateItem(localDate, current = true, active = true))
        for(index in 1..plusDays) {
            val nextDate = localDate.plusDays(index.toLong())
            result.add(DateItem(nextDate, current = false, active = false))
        }
        return result
    }

    data class DateItem(
        val date: LocalDate,
        val current:Boolean,
        val active:Boolean
    )


}