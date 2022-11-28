package com.intern.evtutors.composes.schedule

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.R
import com.intern.evtutors.composes.loading.ui.theme.Purple200
import com.intern.evtutors.composes.loading.ui.theme.Purple500
import com.intern.evtutors.view_models.ScheduleViewModel
import com.miggue.mylogin01.ui.theme.*
import java.time.LocalDate
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklySchedule(
    scheduleViewModel: ScheduleViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(20.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ThirdColor),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeeklyScheduleTab(title = "This week", scheduleViewModel.stateCurrentWeek) {
                    scheduleViewModel.changeWeek("this week")
                }
                WeeklyScheduleTab(title = "Next week", !scheduleViewModel.stateCurrentWeek) {
                    scheduleViewModel.changeWeek("next week")
                }
            }

            AnimatedVisibility(
                visible = scheduleViewModel.stateCurrentWeek,
                enter = slideInHorizontally() + fadeIn(),
                modifier = Modifier.fillMaxWidth().height(200.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if(scheduleViewModel.stateDateOfWeek.isEmpty())
                        scheduleViewModel.getListDayOfWeek()
                    scheduleViewModel.stateDateOfWeek.forEachIndexed() {
                            index, _ ->
                        WeeklyScheduleItem(
                            index,
                            scheduleViewModel,
                            false
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = !scheduleViewModel.stateCurrentWeek,
                enter = slideInHorizontally() + fadeIn(),
                modifier = Modifier.fillMaxWidth().height(200.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if(scheduleViewModel.stateDateOfNextWeek.isEmpty())
                        scheduleViewModel.getListDayOfWeek()
                    scheduleViewModel.stateDateOfNextWeek.forEachIndexed() {
                            index, _ ->
                        WeeklyScheduleItem(
                            index,
                            scheduleViewModel,
                            true
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun WeeklyScheduleTab(
    title:String,
    toggleActive:Boolean,
    onChangeTab: ()->Unit
) {
    val fontFamily = FontFamily(Font(R.font.pacifico_regular))
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(40.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 5.dp)
                .clickable { onChangeTab() },
            text = title,
            fontFamily = fontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.White)
            ,
        )

        AnimatedVisibility(
            visible = toggleActive,
            enter = slideInHorizontally() + fadeIn(),
            modifier = Modifier
                .width(60.dp)
                .height(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Purple500)
                ,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyScheduleItem(
    index:Int,
    scheduleViewModel: ScheduleViewModel,
    nextWeek:Boolean
) {
    var localDate = scheduleViewModel.stateDateOfWeek[index]
    if(nextWeek) {
        localDate = scheduleViewModel.stateDateOfNextWeek[index]
    }
    var borderColor = PrimaryColor
    var backgroundColor = ThirdColor
    if(localDate.current) borderColor = Color.White
    if(localDate.active) backgroundColor = Color.White
    Box(
        modifier = Modifier
            .height(60.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(
                1.dp, borderColor, RoundedCornerShape(30.dp)
            )
            .background(backgroundColor)
            .clickable {
                if(nextWeek) {
                    scheduleViewModel.activeDateOfNextWeek(localDate.date.toString())
                } else {
                    scheduleViewModel.activeDateOfWeek(localDate.date.toString())
                }
            }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = localDate.date.toString().substringAfterLast("-"),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
            Text(
                text = localDate.date.dayOfWeek.toString().substring(0,3),
                fontSize = 6.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
