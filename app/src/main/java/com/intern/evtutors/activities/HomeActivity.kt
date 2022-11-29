package com.intern.evtutors.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cloudinary.android.MediaManager
import com.intern.evtutors.R
import com.intern.evtutors.common.DataLocal
import com.intern.evtutors.navigations.Navigation
//import com.intern.evtutors.common.DataLocal
import com.intern.evtutors.screens.Screens
import com.intern.evtutors.view_models.ProfileViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import com.miggue.mylogin01.ui.theme.ModalColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = HashMap<Any?, Any?>()
        config["cloud_name"] = DataLocal.CLOUD_NAME
        config["api_key"] = DataLocal.API_KEY
        config["api_secret"] = DataLocal.API_SECRET
//        config.put("secure", true);

        MediaManager.init(this, config)
        setContent {
            val viewModel = viewModel<ProfileViewModel>()
            viewModel.getUser()
            val user = viewModel.localUser
            user?.let {
                FatherOfAppsTheme {
                    App(user.roleID)
                }
            }
        }
//        statusBarColor()
    }

    private fun statusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.white, theme)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App(
    TypeOfUser:Int
) {
    val navController = rememberNavController()
    var items = listOf(Screens.StudentHome, Screens.Favourite, Screens.Notification, Screens.Profile)
    if(TypeOfUser == 3) {
        items = listOf(Screens.TutorsHome, Screens.Favourite, Screens.Notification, Screens.TutorProfile)
    }
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach {
                    BottomNavigationItem(
                        selected = currentRoute == it.route,
                        onClick = {
                            navController.navigate(it.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(imageVector = it.icon, contentDescription = null)},
                        label = { Text(text = it.title)},
                    )
                }
            }
        }
    ) {
        if(TypeOfUser!=0) {
            Navigation(
                navController = navController,
                startScreen = items[0].route
            )
        }
    }
}

