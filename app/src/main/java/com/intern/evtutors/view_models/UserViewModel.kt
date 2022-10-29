package com.intern.evtutors.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.intern.evtutors.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor():BaseViewModel() {
    var isUpdating by mutableStateOf(false)

    fun toggleUpdating() {
        isUpdating = !isUpdating
    }
}