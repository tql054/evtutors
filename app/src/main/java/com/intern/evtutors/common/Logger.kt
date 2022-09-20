package com.intern.evtutors.common

import android.util.Log
import com.intern.evtutors.BuildConfig

object Logger {

    fun log(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

}