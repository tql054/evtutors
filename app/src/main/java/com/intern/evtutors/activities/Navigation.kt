package com.intern.evtutors.activities

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.intern.evtutors.activities.ui.Screens
import com.intern.evtutors.composes.home.HomeBaseScreen
import com.intern.evtutors.composes.notification.NotificationScreen
import com.intern.evtutors.composes.profile.ProfileScreen

@Composable
fun Navigation(navController:NavHostController, startScreen:String) {
    NavHost(navController = navController, startDestination = startScreen){
//        Student's Site
        composable(Screens.StudentHome.route) {
            HomeBaseScreen(2)
        }

        composable((Screens.Profile.route)) {
            ProfileScreen(2)
        }

        composable((Screens.Favourite.route)) {
            ProfileScreen(2)
        }

        composable((Screens.Notifitication.route)) {
            NotificationScreen()
        }

//        Tutor's Site
        composable((Screens.TutorsHome.route)) {
            HomeBaseScreen(3)
        }

        composable((Screens.TutorProfile.route)) {
            ProfileScreen(3)
        }
    }
}