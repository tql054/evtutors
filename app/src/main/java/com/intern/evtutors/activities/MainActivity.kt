package com.intern.evtutors.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.intern.evtutors.R
import com.intern.evtutors.base.activities.BaseActivity
import com.intern.evtutors.ui.home.HomeFragment
import com.intern.evtutors.ui.personal.PersonalFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(){

    private var loadingLayout: FrameLayout? = null
    private var bottomNavigation:MeowBottomNavigation?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingLayout = findViewById(R.id.loadingLayout)
        bottomNavigation =  findViewById(R.id.bottom_navigation)
        bottomNavigation?.add(MeowBottomNavigation.Model(1, R.drawable.ic_schedule))
        bottomNavigation?.add(MeowBottomNavigation.Model(2, R.drawable.ic_heart))
        bottomNavigation?.add(MeowBottomNavigation.Model(3, R.drawable.ic_bell))
        bottomNavigation?.add(MeowBottomNavigation.Model(4, R.drawable.ic_person))
        loadFragment(HomeFragment())
        bottomNavigation?.setOnShowListener {
            when (it.id) {
                1 -> {
                    loadFragment(HomeFragment())
                }

                2 -> {
                    loadFragment(PersonalFragment())
                }

                3 -> {
                    loadFragment(PersonalFragment())
                }

                4 -> {
                    loadFragment(PersonalFragment())
                }
            }
        }

        bottomNavigation?.show(1, true)
        bottomNavigation?.setOnClickMenuListener {
            fun onClickItem(item: MeowBottomNavigation.Model?) {}
        }

        bottomNavigation?.setOnReselectListener {
            fun onReselectItem(item: MeowBottomNavigation.Model?) {}
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE")
        super.onSaveInstanceState(outState)
    }

    override fun showLoading(isShow: Boolean) {
        loadingLayout?.visibility = if(isShow) View.VISIBLE else View.GONE
    }


}