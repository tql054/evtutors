package com.intern.evtutors.composes.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.view_models.LessonViewModel
import com.intern.evtutors.view_models.ScheduleViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = hiltViewModel(),
    openCreateTestScreen: (lessonId:Int) -> Unit
) {
    FatherOfAppsTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Title()
            HeaderLine()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                WeeklySchedule(scheduleViewModel)
                Spacer(Modifier.height(10.dp))
                TimeSchedule(
                    scheduleViewModel,
                    openCreateTestScreen
                )
            }
        }

    }
}

@Composable
fun Title() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
//        horizontalArrangement = Arrangement.Start,
//        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(32.dp)
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back Button"
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            text = "My schedule",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun HeaderLine() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    FatherOfAppsTheme {
//        ScheduleScreen(), {}
    }
}
