package com.intern.evtutors.view_models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.model_json.LessonDetailJson
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.data.repositories.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
):BaseViewModel() {
    var stateLesson by mutableStateOf<LessonDetailJson?>(null)

    fun getLessonById(idLesson:Int) {
        viewModelScope.launch(handler) {
            stateLesson = lessonRepository.getLessonById(idLesson)
        }
    }

}