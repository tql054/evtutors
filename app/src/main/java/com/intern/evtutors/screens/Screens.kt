package com.intern.evtutors.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screens(val route:String, val title:String, val icon:ImageVector) {
    object StudentHome: Screens(
        route = "home/student",
        title = "Home",
        icon = Icons.Default.Home
    )

    object TutorsHome: Screens(
        route = "home/tutor",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favourite: Screens(
        route = "favourite",
        title = "Schedule",
        icon = Icons.Default.List
    )

    object Notification: Screens(
        route = "notification",
        title = "News",
        icon = Icons.Default.Notifications
    )

    object Profile: Screens(
        route = "profile",
        title = "Me",
        icon = Icons.Default.Person
    )

    object TutorProfile: Screens(
        route = "profile/tutor",
        title = "Me",
        icon = Icons.Default.Person
    )
}