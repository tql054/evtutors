package com.intern.evtutors.composes.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intern.evtutors.composes.home.CircleAvatar
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.view_models.ScheduleViewModel
import com.miggue.mylogin01.ui.theme.FourthColor
import com.miggue.mylogin01.ui.theme.PrimaryColor
import com.miggue.mylogin01.ui.theme.SecondaryColor
import com.miggue.mylogin01.ui.theme.ThirdColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeSchedule(
    scheduleViewModel: ScheduleViewModel,
    openCreateTestScreen: (lessonId:Int) -> Unit
) {
    scheduleViewModel.getTeacherLessonByDate()
    Scaffold(
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 350.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(FourthColor)
                    .padding(10.dp),
            ) {
                item {
                    if(scheduleViewModel.stateListLesson == mutableListOf<Lesson>()) {
                        for (i in 1..6) {
                            Column() {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(.5.dp)
                                        .padding(start = 5.dp)
                                        .background(SecondaryColor)
                                )
                                Spacer(modifier = Modifier.height(70.dp))
                            }
                        }
                    } else {
                        TimeScheduleItem(
                            scheduleViewModel,
                            openCreateTestScreen
                        )
                    }
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeScheduleItem(
    scheduleViewModel: ScheduleViewModel,
    openCreateTestScreen: (lessonId:Int) -> Unit
) {
    val listLesson = scheduleViewModel.stateListLesson
    Column {
        for(lesson in listLesson) {
            LineOfTime(time = lesson.timeStart.substringAfter(" ").substringBeforeLast(":00", ""))
            LessonInfo(lesson.courseId) {
                openCreateTestScreen(lesson.id)
            }
            LineOfTime(time = lesson.timeEnd.substringAfter(" ").substringBeforeLast(":00", ""))
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun LineOfTime(time:String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
       Text(
           text =time,
           fontSize = 12.sp,
           fontWeight = FontWeight.Bold
       )
        Box(
           modifier = Modifier
               .weight(1f)
               .height(.5.dp)
               .padding(start = 5.dp)
               .background(SecondaryColor)
        )
    }
}

@Composable
fun LessonInfo(
    courseId:Int,
    onNavigate: ()->Unit
) {
    val cornerShape = RoundedCornerShape(15.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start=40.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(cornerShape)
                .border(1.dp, PrimaryColor, cornerShape)
                .background(ThirdColor)
                .clickable { onNavigate() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(20.dp, 0.dp),
                text = "Lesson of course $courseId",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
            )
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(70.dp)
                    .background(PrimaryColor)
                    .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))
            )
        }
    }
}
