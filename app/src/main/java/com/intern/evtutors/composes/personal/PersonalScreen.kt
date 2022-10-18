package com.intern.evtutors.composes.personal

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.lang.reflect.Modifier

@Composable
fun PersonalScreen(
    modifier: Modifier = Modifier()
) {
    Surface() {
        Text(text = "User's information page")
    }
}

