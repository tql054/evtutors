package com.intern.evtutors.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.intern.evtutors.R
import com.intern.evtutors.base.activities.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(){

    private var loadingLayout: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("Frank","MainActivity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingLayout = findViewById(R.id.loadingLayout)
    }

    override fun showLoading(isShow: Boolean) {
        loadingLayout?.visibility = if(isShow) View.VISIBLE else View.GONE
    }


}