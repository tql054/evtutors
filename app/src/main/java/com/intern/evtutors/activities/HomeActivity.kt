package com.intern.evtutors.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.intern.evtutors.activities.ui.Screens
import com.intern.evtutors.ui.customer.login.LoginViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = viewModel<LoginViewModel>()
            viewModel.getuser()
            val user = viewModel.localUser
            user?.let {
                FatherOfAppsTheme() {
                    App(user.roleID)
                }
            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App(
    TypeOfUser:Int
) {
    val navController = rememberNavController()
    var items = listOf(Screens.StudentHome, Screens.Favourite, Screens.Notifitication, Screens.Profile)
    if(TypeOfUser == 3) {
        items = listOf(Screens.TutorsHome, Screens.Favourite, Screens.Notifitication, Screens.TutorProfile)
    }
    Scaffold(
        bottomBar = {
            BottomNavigation() {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach() {
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
            Navigation(navController = navController, startScreen = items[0].route)
        }
    }
}

