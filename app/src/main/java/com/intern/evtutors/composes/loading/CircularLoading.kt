package com.intern.evtutors.composes.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miggue.mylogin01.ui.theme.PrimaryColor

@Composable
fun CircularLoading(
    size:Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size.dp)
                .padding(top=100.dp)
                .align(Alignment.TopCenter),
            color = PrimaryColor
        )
    }
}