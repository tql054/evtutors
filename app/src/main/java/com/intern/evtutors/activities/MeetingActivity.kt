package com.intern.evtutors.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.FrameLayout
import com.intern.evtutors.R
import com.intern.evtutors.base.activities.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeetingActivity:BaseActivity() {
    private var loadingLayout:FrameLayout?=null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_meeting)
        loadingLayout = findViewById(R.id.loadingLayout)

    }

    override fun showLoading(isShow: Boolean) {
        loadingLayout?.visibility = if(isShow) View.VISIBLE else View.GONE
    }

}