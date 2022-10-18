package com.intern.evtutors.activities.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route:String, val title:String, val icon:ImageVector) {
    object Home:Screen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favourite:Screen(
        route = "personal",
        title = "Info",
        icon = Icons.Default.Star
    )

    object Notifitication:Screen(
        route = "personal",
        title = "Info",
        icon = Icons.Default.Notifications
    )

    object Personal:Screen(
        route = "personal",
        title = "Info",
        icon = Icons.Default.Person
    )
}