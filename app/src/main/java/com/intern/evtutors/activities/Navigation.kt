package com.intern.evtutors.activities

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.intern.evtutors.activities.ui.Screen
import com.intern.evtutors.composes.home.HomeBaseScreen
import com.intern.evtutors.composes.personal.PersonalScreen

@Composable
fun Navigation(navController:NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route) {
            HomeBaseScreen()
        }

        composable((Screen.Personal.route)) {
            PersonalScreen()
        }

        composable((Screen.Favourite.route)) {
            PersonalScreen()
        }

        composable((Screen.Notifitication.route)) {
            PersonalScreen()
        }
    }
}