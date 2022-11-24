package com.intern.evtutors.composes.schedule

import androidx.compose.foundation.background
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
import com.miggue.mylogin01.ui.theme.FourthColor
import com.miggue.mylogin01.ui.theme.PrimaryColor
import com.miggue.mylogin01.ui.theme.SecondaryColor
import com.miggue.mylogin01.ui.theme.ThirdColor

@Composable
fun TimeSchedule() {
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
                    TimeScheduleItem()
                }
            }
        }
    )
}

@Composable
fun TimeScheduleItem(
    //Lesson Object
) {
    Column {
        LineOfTime(time = "9:00")
        LessonInfo()
        LineOfTime(time = "11:00")
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun LineOfTime(time:String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
       Text(
           text =time,
           fontSize = 12.sp
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
    //Lesson Object
    //idUser -> role
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start=40.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(70.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(ThirdColor)
                .padding(20.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "TOEIC T107",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                CircleAvatar()
                Text(
                    text = "Dinh Khoa",
                    fontSize = 8.sp,
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun TimeSchedulePreview() {
    TimeSchedule()
}
