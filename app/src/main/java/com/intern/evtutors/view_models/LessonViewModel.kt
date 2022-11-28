package com.intern.evtutors.view_models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.Lesson
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor():BaseViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getLessonByDate(date:String) {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        var localDateBegin = LocalDate.parse(date, formatter)
        Log.d("Date formatted:", localDateBegin.toString())
        val listLesson = mutableListOf<Lesson>(
            Lesson(1, 2, 0, "11/19/2022 15:30:00", "11/19/2022 15:30:00"),
            Lesson(2, 2, 0, "11/19/2022 18:30:00", "11/19/2022 15:30:00")
        )
    }
}