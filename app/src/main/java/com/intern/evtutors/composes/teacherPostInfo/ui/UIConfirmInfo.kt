package com.intern.evtutors.composes.teacherPostInfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.data.models.Role
import com.intern.evtutors.data.models.TeacherPost
import com.intern.evtutors.data.models.TimePost
import com.intern.evtutors.view_models.TeacherPostViewModel
import com.miggue.mylogin01.ui.theme.*

@Composable
fun UIConfirmInfo(input: TeacherPost){
    Column(Modifier.fillMaxWidth(0.9f)) {
        Text(text= "Please confirm your class information", fontSize = 13.sp ,color = Blue300, fontStyle = FontStyle.Italic )
        Spacer(Modifier.height(15.dp))
        Text(text = "Details", color = Color.Black, fontSize = 20.sp )
        ItemShowInfoText("Subjects",input.Subjects)
        ItemShowInfoText("Study form",input.Form)
        ItemShowInfoText("Tutor",input.TutorName)
        Spacer(Modifier.height(15.dp))

        Text(text = "Time", color = Color.Black, fontSize = 20.sp, )
        ItemShowInfoText("Number of lessons",input.ShiftsCount.toString())
        ItemShowInfoText("Start day",input.Startday)
        ItemShowInfoText("Schedule","  ")

        UIschedule()


        Text(text = "Contact", color = Color.Black, fontSize = 20.sp, )
        Text(text = input.Phone, color = GrayText, fontSize = 15.sp)
        Text(text = input.Adress, color = GrayText, fontSize = 15.sp)
        Spacer(Modifier.height(55.dp))

    }
}

@Composable
fun ItemShowInfoText(name:String,info:String){
    Spacer(Modifier.height(1.dp))
    Row(Modifier.fillMaxWidth(),
        Arrangement.SpaceBetween
    ){
        Text(text = name, color = Gray200, fontSize = 15.sp)

        Text(text = info, color = Gray200, fontSize = 15.sp)
    }
}

@Composable
fun UIschedule(teacherPostViewModel: TeacherPostViewModel = hiltViewModel()){
    Column(Modifier.fillMaxWidth()){
        Column{

            ItemSchedule("T2",teacherPostViewModel.timeMonday)
            Spacer(Modifier.fillMaxWidth().height(1.dp).background(Grayy100))
            ItemSchedule("T3",teacherPostViewModel.timeTuesday)
            Spacer(Modifier.fillMaxWidth().height(1.dp).background(Grayy100))
            ItemSchedule("T4",teacherPostViewModel.timeWednesday)
            Spacer(Modifier.fillMaxWidth().height(1.dp).background(Grayy100))
            ItemSchedule("T5",teacherPostViewModel.timeThursday)
            Spacer(Modifier.fillMaxWidth() .height(1.dp).background(Grayy100))
            ItemSchedule("T6",teacherPostViewModel.timeFriday)
            Spacer(Modifier.fillMaxWidth() .height(1.dp).background(Grayy100))
            ItemSchedule("T7",teacherPostViewModel.timeSaturday)
            Spacer(Modifier.fillMaxWidth() .height(1.dp).background(Grayy100))
            ItemSchedule("CN",teacherPostViewModel.timeSunday)
            Spacer(Modifier.fillMaxWidth().height(1.dp).background(Grayy100))
        }

    }


}
@Composable
fun ItemUIschedule(timeStart:String?,timeEnd:String?){
    Row(Modifier.fillMaxWidth().height(40.dp)
        ){
        Row(Modifier.width(90.dp).fillMaxHeight().background(LightBlue)
            ,Arrangement.Center,Alignment.CenterVertically){
            Text(text = "$timeStart-$timeEnd", fontSize = 13.sp)
        }
        Spacer(Modifier.fillMaxHeight().width(1.dp))

    }
}
@Composable
fun ItemSchedule(Nameday:String,lisi:List<TimePost>){
    Row(Modifier.fillMaxWidth()
        .height(40.dp),

        Arrangement.Center,
        Alignment.CenterVertically
    ){
        Row(Modifier.width(40.dp)
            .height(40.dp)
            .background(Grayy100),
            Arrangement.Center,
            Alignment.CenterVertically
        ){
            Text(text = Nameday)
        }
        Spacer(Modifier.fillMaxHeight().width(1.dp).background(Grayy100))


        LazyRow(Modifier.fillMaxSize()){
            items(items =lisi){ day ->
                ItemUIschedule(day.TimeStrart,day.TimeEnd)
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    var timePost=TimePost(1,"monday","10:30","12:30")
//    var time: MutableSet<TimePost> = mutableSetOf(timePost)
//    FatherOfAppsTheme {
//        UIConfirmInfo(
//            TeacherPost("Toan","Online",2,
//                time,"11/22/2022","09123456","tieen ha"
//            )
//        )
//
//    }
//}