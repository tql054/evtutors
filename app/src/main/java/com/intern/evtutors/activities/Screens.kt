package com.intern.evtutors.activities.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screens(val route:String, val title:String, val icon:ImageVector) {
    object StudentHome:Screens(
        route = "home/student",
        title = "Home",
        icon = Icons.Default.Home
    )

    object TutorsHome:Screens(
        route = "home/tutor",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favourite:Screens(
        route = "favourite",
        title = "",
        icon = Icons.Default.Star
    )

    object Notifitication:Screens(
        route = "notification",
        title = "",
        icon = Icons.Default.Notifications
    )

    object Profile:Screens(
        route = "profile",
        title = "",
        icon = Icons.Default.Person
    )

    object TutorProfile:Screens(
        route = "profile/tutor",
        title = "",
        icon = Icons.Default.Person
    )
}