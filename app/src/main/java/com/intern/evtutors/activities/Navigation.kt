package com.intern.evtutors.activities

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.intern.evtutors.activities.ui.Screens
import com.intern.evtutors.composes.home.HomeBaseScreen
import com.intern.evtutors.composes.personal.PersonalScreen

@Composable
fun Navigation(navController:NavHostController, startScreen:String) {
    NavHost(navController = navController, startDestination = startScreen){
//        Student's Site
        composable(Screens.StudentHome.route) {
            HomeBaseScreen(2)
        }

        composable((Screens.Personal.route)) {
            PersonalScreen(2)
        }

        composable((Screens.Favourite.route)) {
            PersonalScreen(2)
        }

        composable((Screens.Notifitication.route)) {
            PersonalScreen(2)
        }

//        Tutor's Site
        composable((Screens.TutorsHome.route)) {
            HomeBaseScreen(3)
        }

        composable((Screens.TutorPersonal.route)) {
            PersonalScreen(3)
        }
    }
}