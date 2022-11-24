package com.intern.evtutors.navigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.intern.evtutors.screens.Screens
import com.intern.evtutors.composes.home.HomeBaseScreen
import com.intern.evtutors.composes.notification.NotificationScreen
import com.intern.evtutors.composes.profile.ProfileScreen
import com.intern.evtutors.composes.schedule.ScheduleScreen
import com.intern.evtutors.ui.customer.profile.Profile_Greeting
import com.intern.evtutors.ui.customer.profile.profileTeacher

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController:NavHostController, startScreen:String) {
    NavHost(navController = navController, startDestination = startScreen){
//        Student's Site
        composable(Screens.StudentHome.route) {
            HomeBaseScreen(navController,2)
        }

        composable((Screens.Profile.route)) {
//            ProfileScreen(2)
            Profile_Greeting(navController)
        }

        composable((Screens.Favourite.route)) {
            ScheduleScreen()
        }

        composable((Screens.Notification.route)) {
            NotificationScreen()
        }

//        Tutor's Site
        composable((Screens.TutorsHome.route)) {
            HomeBaseScreen(navController,3)
        }

        composable((Screens.TutorProfile.route)) {
//            ProfileScreen(3)
            Profile_Greeting(navController)
        }

        composable("home/profile/certificates") {
            ProfileScreen(navController,3)
        }
        composable("home/teacher") {
            profileTeacher(navController)
        }

        navigation(route = "lesson", startDestination = "lesson/details") {
            composable(route="lesson/details"){

            }
        }
    }
}