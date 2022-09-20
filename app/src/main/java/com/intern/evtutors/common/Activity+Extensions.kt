package com.intern.evtutors.common

import android.app.Activity
import com.intern.evtutors.CustomApplication

val Activity.customApplication: CustomApplication
get() = application as CustomApplication