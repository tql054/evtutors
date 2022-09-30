package com.intern.evtutors.activities

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.intern.evtutors.R
import com.intern.evtutors.base.activities.BaseActivity
import com.intern.evtutors.ui.videocall.DemoStreamFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeetingActivity:BaseActivity() {
    private var container: FragmentContainerView?=null
    lateinit var loadingLayout:FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting)
        loadingLayout = findViewById(R.id.loadingLayout)
        container = findViewById(R.id.container)
        val lesson = intent.getSerializableExtra("lesson")
        val bundle = bundleOf("lesson" to lesson)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.meeting_nav, bundle)
    }


    override fun showLoading(isShow: Boolean) {
        loadingLayout?.visibility = if(isShow) View.VISIBLE else View.GONE
    }

}