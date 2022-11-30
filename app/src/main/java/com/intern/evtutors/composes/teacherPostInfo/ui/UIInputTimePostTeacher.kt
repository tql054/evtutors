package com.intern.evtutors.composes.teacherPostInfo.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.sharp.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.R
import com.intern.evtutors.data.models.TimePost
import com.intern.evtutors.view_models.TeacherPostViewModel
import com.miggue.mylogin01.ui.theme.Blue300
import com.miggue.mylogin01.ui.theme.Brown300
import com.miggue.mylogin01.ui.theme.whitebacground
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay

import java.util.*


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UIInputTimePostTeacher(onchaneCount:(Int)->Unit){
    var colorItem1 by remember{ mutableStateOf(whitebacground) }
    var colorItem2 by remember{ mutableStateOf(whitebacground) }
    var colorItem3 by remember{ mutableStateOf(whitebacground) }
    var colorItem4 by remember{ mutableStateOf(whitebacground) }
    var colorItem5 by remember{ mutableStateOf(whitebacground) }
    var colorItem6 by remember{ mutableStateOf(whitebacground) }
    var colorItem7 by remember{ mutableStateOf(whitebacground) }
    var colorStatus by remember{ mutableStateOf(0) }
    onchaneCount(colorStatus)

    Column(Modifier.fillMaxWidth(0.9f)) {
        Text(text = "NUMBER OF LESSONS PER WEEK", color = Color.Gray)
        when(colorStatus){
            1->{
                colorItem1= Blue300
                colorItem2=whitebacground
                colorItem3=whitebacground
                colorItem4=whitebacground
                colorItem5=whitebacground
                colorItem6=whitebacground
                colorItem7=whitebacground
            }
            2->{
                colorItem1=whitebacground
                colorItem2= Blue300
                colorItem3=whitebacground
                colorItem4=whitebacground
                colorItem5=whitebacground
                colorItem6=whitebacground
                colorItem7=whitebacground
            }
            3->{
                colorItem1=whitebacground
                colorItem2=whitebacground
                colorItem3=Blue300
                colorItem4=whitebacground
                colorItem5=whitebacground
                colorItem6=whitebacground
                colorItem7=whitebacground
            }
            4->{
                colorItem1=whitebacground
                colorItem2=whitebacground
                colorItem3=whitebacground
                colorItem4=Blue300
                colorItem5=whitebacground
                colorItem6=whitebacground
                colorItem7=whitebacground
            }
            5->{
                colorItem1=whitebacground
                colorItem2=whitebacground
                colorItem3=whitebacground
                colorItem4=whitebacground
                colorItem5=Blue300
                colorItem6=whitebacground
                colorItem7=whitebacground
            }
            6->{
                colorItem1=whitebacground
                colorItem2= whitebacground
                colorItem3=whitebacground
                colorItem4=whitebacground
                colorItem5=whitebacground
                colorItem6=Blue300
                colorItem7=whitebacground
            }
            7->{
                colorItem1=whitebacground
                colorItem2= whitebacground
                colorItem3=whitebacground
                colorItem4=whitebacground
                colorItem5=whitebacground
                colorItem6=whitebacground
                colorItem7=Blue300
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            Arrangement.Center){
            itemDate("1 Lesson",colorItem1, onClick = {
                colorStatus=1
                onchaneCount(colorStatus)
            })
            itemDate("2 Lesson",colorItem2,onClick = {
                colorStatus=2
                onchaneCount(colorStatus)
            })
            itemDate("3 Lesson",colorItem3,onClick = {
                colorStatus=3
                onchaneCount(colorStatus)
            })
            itemDate("4 Lesson",colorItem4,onClick = {
                colorStatus=4
                onchaneCount(colorStatus)
            })
        }
        Spacer(Modifier.height(10.dp))
        Row(
            Modifier.fillMaxWidth(),
            Arrangement.Center){
            itemDate("5 Lesson",colorItem5,onClick = {
                colorStatus=5
                onchaneCount(colorStatus)
            })
            itemDate("6 Lesson",colorItem6,onClick = {
                colorStatus=6
                onchaneCount(colorStatus)
            })
            itemDate("7 Lesson",colorItem7,onClick = {
                colorStatus=7
                onchaneCount(colorStatus)
            })
        }
        Spacer(Modifier.height(20.dp))
        Text(text = "CREATE A CLASS SCHDULE", color = Color.Gray)

        itemTimeInDay("Monday")
        itemTimeInDay("Tuesday")
        itemTimeInDay("Wednesday")
        itemTimeInDay("Thursday")
        itemTimeInDay("Friday")
        itemTimeInDay("Saturday")
        itemTimeInDay("Sunday")

    }
}
@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun itemTimeInDay(nameDay:String,teacherPostViewModel: TeacherPostViewModel = hiltViewModel()){

    var timeMonday by mutableStateOf(mutableListOf<String>())
    var time by remember { mutableStateOf("")}
    var timePost by remember { mutableStateOf(TimePost(teacherPostViewModel.timeMonday.size+1,nameDay,null,null)) }

    Box(Modifier.fillMaxWidth().heightIn(110.dp,260.dp).wrapContentHeight()){
        Column(
            Modifier.fillMaxWidth().heightIn(100.dp,250.dp).wrapContentHeight()
                .padding(start = 15.dp, top = 5.dp, end = 20.dp, bottom = 10.dp)
                .border(width = 1.dp, color = Brown300, shape = RoundedCornerShape(10.dp))
        ) {

            Spacer(Modifier.height(18.dp))


            LazyColumn(
                modifier = Modifier.padding(vertical = 1.dp).fillMaxWidth()
            ) {

                when(nameDay){
                    "Monday"->{
                        items(items = teacherPostViewModel.timeMonday){ day ->
                            ItemHoursShifts(day,"Monday"
                                , onClick = {
                                    teacherPostViewModel.updateTime("Monday",
                                        TimePost(day.id,"Monday",it.TimeStrart,it.TimeEnd))})
                            Spacer(Modifier.height(1.dp))
                        }
                    }
                    "Tuesday"->{
                        items(items = teacherPostViewModel.timeTuesday){ day ->
                            ItemHoursShifts(day,"Tuesday"
                                , onClick = {teacherPostViewModel.updateTime("Tuesday",it)})
                            Spacer(Modifier.height(1.dp))
                        }
                    }
                "Wednesday"->{
                    items(items = teacherPostViewModel.timeWednesday){ day ->
                        ItemHoursShifts(day,"Wednesday"
                            , onClick = {teacherPostViewModel.updateTime("Wednesday",it)})
                        Spacer(Modifier.height(1.dp))
                    }
                }
                "Friday"->{
                    items(items = teacherPostViewModel.timeFriday){ day ->
                        ItemHoursShifts(day,"Friday"
                            , onClick = {teacherPostViewModel.updateTime("Friday",it)})
                        Spacer(Modifier.height(1.dp))
                    }
                }
                "Saturday"->{
                    items(items = teacherPostViewModel.timeSaturday){ day ->
                        ItemHoursShifts(day,"Saturday"
                            , onClick = {teacherPostViewModel.updateTime("Saturday",it)})
                        Spacer(Modifier.height(1.dp))
                    }
                }
                "Sunday"->{
                    items(items = teacherPostViewModel.timeSunday){ day ->
                        ItemHoursShifts(day,"Sunday"
                            , onClick = {teacherPostViewModel.updateTime("Sunday",it)})
                        Spacer(Modifier.height(1.dp))
                    }
                }
                "Thursday"->{
                    items(items = teacherPostViewModel.timeThursday){ day ->
                        ItemHoursShifts(day,"Thursday"
                            , onClick = {teacherPostViewModel.updateTime("Thursday",it)})
                        Spacer(Modifier.height(1.dp))
                    }
                }
                }
            }
            Row(Modifier.fillMaxWidth().padding(end = 5.dp, bottom = 5.dp),
                Arrangement.End) {
                Row(Modifier.height(25.dp)
                    .clickable {
                        when(nameDay){
                            "Monday"->{
//
                                val int =teacherPostViewModel.timeMonday.size
                                if(int==0){
                                    time= (teacherPostViewModel.timeMonday.size+1).toString()
                                    timePost= TimePost(teacherPostViewModel.timeMonday.size+1,"Monday",null,null)
                                }else{
                                    val i= teacherPostViewModel.timeMonday[int-1].id!! +1
                                    timePost= TimePost(i,"Monday",null,null)
                                }
                            }
                            "Tuesday"->{
                                val int =teacherPostViewModel.timeTuesday.size
                                if(int==0){
                                    timePost= TimePost(teacherPostViewModel.timeTuesday.size+1,"Tuesday",null,null)
                                }else{
                                    val i= teacherPostViewModel.timeTuesday[int-1].id!! +1
                                    timePost= TimePost(i,"Tuesday",null,null)
                                }

                            }
                            "Wednesday"->{
                                val int =teacherPostViewModel.timeWednesday.size
                                if(int==0){
                                    timePost= TimePost(teacherPostViewModel.timeWednesday.size+1,"Tuesday",null,null)
                                }else{
                                    val i= teacherPostViewModel.timeWednesday[int-1].id!! +1
                                    timePost= TimePost(i,"Wednesday",null,null)
                                }
                            }
                            "Thursday"->{
                                val int =teacherPostViewModel.timeThursday.size
                                if(int==0){
                                    timePost= TimePost(teacherPostViewModel.timeThursday.size+1,"Tuesday",null,null)
                                }else{
                                    val i= teacherPostViewModel.timeThursday[int-1].id!! +1
                                    timePost= TimePost(i,"Thursday",null,null)
                                }
                            }
                            "Friday"->{
                                val int =teacherPostViewModel.timeFriday.size
                                if(int==0){
                                    timePost= TimePost(teacherPostViewModel.timeFriday.size+1,"Tuesday",null,null)
                                }else{
                                    val i= teacherPostViewModel.timeFriday[int-1].id!! +1
                                    timePost= TimePost(i,"Friday",null,null)
                                }
                            }
                            "Saturday"->{
                                val int =teacherPostViewModel.timeSaturday.size
                                if(int==0){
                                    timePost= TimePost(teacherPostViewModel.timeSaturday.size+1,"Tuesday",null,null)
                                }else{
                                    val i= teacherPostViewModel.timeSaturday[int-1].id!! +1
                                    timePost= TimePost(i,"Saturday",null,null)
                                }
                            }
                            "Sunday"->{
                                val int =teacherPostViewModel.timeSunday.size
                                if(int==0){
                                    timePost= TimePost(teacherPostViewModel.timeSunday.size+1,"Tuesday",null,null)
                                }else{
                                    val i= teacherPostViewModel.timeSunday[int-1].id!! +1
                                    timePost= TimePost(i,"Sunday",null,null)
                                }
                            }
                        }

                        teacherPostViewModel.addtime(time,timePost,nameDay)
                    }
                    .width(65.dp).background(Blue300, shape = RoundedCornerShape(20.dp)),
                    Arrangement.SpaceAround,
                    Alignment.CenterVertically
                ) {
                    Text(text = "ADD", color = whitebacground)
                }
            }


        }
        Row(Modifier.padding(start = 25.dp)) {
            Row(Modifier.background(whitebacground)) {
                Text(text=nameDay, fontSize = 15.sp, color = Blue300) } }
    }


}
@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ItemHoursShifts1(i:String,nameDay: String,
                    onClick: (TimePost) -> Unit,
                    teacherPostViewModel: TeacherPostViewModel= hiltViewModel()){

    val calendarStart = Calendar.getInstance()
    val calendarEnd = Calendar.getInstance()
    val context = LocalContext.current
    val timeStartHours =calendarStart[Calendar.HOUR_OF_DAY]
    val timeStartMinute =calendarStart[Calendar.MINUTE]
    var timeStrart by remember{ mutableStateOf("") }
    var check by remember{ mutableStateOf(true) }

    var timeEndHours =calendarEnd.get(Calendar.HOUR)
    var timeEndMinute =calendarEnd.get(Calendar.MINUTE)
    var timeEnd by remember{ mutableStateOf("") }

    var timeText by  mutableStateOf(TimePost(0,"text","",""))

    val TimePickerDialogStart= TimePickerDialog(
        context,
        {_, timeStartHours : Int, timeStartMinute: Int ->
            timeStrart="$timeStartHours:$timeStartMinute"
        }, timeStartHours, timeStartMinute,  true
    )
    val TimePickerDialogEnd= TimePickerDialog(
        context,
        {_, timeEndHours : Int, timeEndMinute: Int ->
            timeEnd="$timeEndHours:$timeEndMinute"
        }, timeEndHours, timeEndMinute,  true
    )
    timeText.TimeEnd=timeEnd
    timeText.TimeStrart=timeStrart
    Row(Modifier.fillMaxWidth()
        .padding(start = 5.dp, end = 20.dp)
    ) {
        Row(Modifier.height(30.dp).width(50.dp).background(Color(0xFFD9D9D9))
            .clickable {

                TimePickerDialogStart.show()


            },
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ){
            Text(text = timeText.TimeStrart.toString())
            // Text(text =id.toString() )
        }
        Row(Modifier.height(30.dp).width(25.dp),
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ) {
            Text(text = "->")
        }
        Row(Modifier.height(30.dp).width(50.dp).background(Color(0xFFD9D9D9))
            .clickable {
                TimePickerDialogEnd.show()

            },
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ){
            Text(text = timeText.TimeEnd.toString())
        }
        Row(Modifier.height(30.dp).width(50.dp)
            .clickable {

                i?.let { teacherPostViewModel.deletetime(it.toInt(),nameDay) }

            },
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ){
            Icon(
                Icons.Filled.Close, contentDescription = null, Modifier.size(18.dp),
                Color.Red
            )
        }
        if(check){
            Row(Modifier.height(30.dp).width(50.dp)
                .clickable {

                    onClick(TimePost(i.toInt(),nameDay,timeStrart,timeEnd))
                    check=false
                },
                Arrangement.SpaceAround,
                Alignment.CenterVertically
            ){
                Icon(
                    Icons.Filled.Check, contentDescription = null, Modifier.size(18.dp),
                    Blue300
                )
            }
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ItemHoursShifts(timePost:TimePost,nameDay: String,
                        onClick: (TimePost) -> Unit,
                    teacherPostViewModel: TeacherPostViewModel= hiltViewModel()){

    val calendarStart = Calendar.getInstance()
    val calendarEnd = Calendar.getInstance()
    val context = LocalContext.current
    val timeStartHours =calendarStart[Calendar.HOUR_OF_DAY]
    val timeStartMinute =calendarStart[Calendar.MINUTE]
    var timeStrart by remember{ mutableStateOf("") }
    var check by remember{ mutableStateOf(true) }
    val id=timePost.id
    var timeEndHours =calendarEnd.get(Calendar.HOUR)
    var timeEndMinute =calendarEnd.get(Calendar.MINUTE)
    var timeEnd by remember{ mutableStateOf("") }

    var timeText by  mutableStateOf(TimePost(0,"text","",""))
    var checkdailog by remember{ mutableStateOf(false) }
    val TimePickerDialogStart= TimePickerDialog(
        context,
        {_, timeStartHours : Int, timeStartMinute: Int ->
            timeStrart="$timeStartHours:$timeStartMinute"
        }, timeStartHours, timeStartMinute,  true
    )
    val TimePickerDialogEnd= TimePickerDialog(
        context,
        {_, timeEndHours : Int, timeEndMinute: Int ->
            timeEnd="$timeEndHours:$timeEndMinute"
        }, timeEndHours, timeEndMinute,  true
    )

    if(checkdailog){

        TimePickerDialog(onchane = {timeStrart=it})
    }

    timeText.TimeEnd=timeEnd
    timeText.TimeStrart=timeStrart
    Row(Modifier.fillMaxWidth()
        .padding(start = 5.dp, end = 20.dp)
    ) {
//        Text(text=timePost.id.toString())
        Row(Modifier.height(30.dp).width(50.dp).background(Color(0xFFD9D9D9))
            .clickable {

                TimePickerDialogStart.show()
                //checkdailog=true

            },
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ){
            if(timeText.TimeStrart.toString().isNotEmpty()){
                Text(text = timeText.TimeStrart.toString())

            }else{
                if(timePost.TimeStrart!=null)
                    Text(text = timePost.TimeStrart.toString())
                else
                    Text(text = "")
            }

            //Text(text =id.toString() )
        }
        Row(Modifier.height(30.dp).width(25.dp),
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ) {
            Text(text = "->")
        }
        Row(Modifier.height(30.dp).width(50.dp).background(Color(0xFFD9D9D9))
            .clickable {
                TimePickerDialogEnd.show()


                //AlertDialogBoxs()
            },
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ){
            if(timeText.TimeEnd.toString().isNotEmpty()){
                Text(text = timeText.TimeEnd.toString())

            }else{
                if(timePost.TimeEnd!=null)
                    Text(text = timePost.TimeEnd.toString())
                else
                    Text(text = "")
            }
        }
        Row(Modifier.height(30.dp).width(50.dp)
            .clickable {

                id?.let { teacherPostViewModel.deletetime(it,nameDay) }

            },
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ){
            Icon(
                Icons.Filled.Close, contentDescription = null, Modifier.size(18.dp),
                Color.Red
            )
        }
        if(check){
            Row(Modifier.height(30.dp).width(50.dp)
                .clickable {

                    onClick(TimePost(id,nameDay,timeStrart,timeEnd))
                    check=false
                },
                Arrangement.SpaceAround,
                Alignment.CenterVertically
            ){
                Icon(
                    Icons.Filled.Check, contentDescription = null, Modifier.size(18.dp),
                    Blue300
                )
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TimePickerDialog(onchane: (String) -> Unit){

    val calendarStart = Calendar.getInstance()

    val context = LocalContext.current
    val timeStartHours =calendarStart[Calendar.HOUR_OF_DAY]
    val timeStartMinute =calendarStart[Calendar.MINUTE]
    var timeStrart by remember{ mutableStateOf("") }
    val TimePickerDialogStart= TimePickerDialog(
        context,
        {_, timeStartHours : Int, timeStartMinute: Int ->
            timeStrart="$timeStartHours:$timeStartMinute"
        }, timeStartHours, timeStartMinute,  true
    )

        TimePickerDialogStart.show()
    onchane(timeStrart)
}

@Composable
fun itemDate(number:String,color: Color,
            onClick:()->Unit){
    Row(Modifier.width(75.dp).height(35.dp).background(color,shape = RoundedCornerShape(10.dp))
        .clickable {
            onClick()
        }
        .border(width = 1.dp, color = Blue300 ,shape = RoundedCornerShape(10.dp)),
        Arrangement.SpaceAround,
        Alignment.CenterVertically

    ){
        Text(text = number, fontSize = 15.sp, fontWeight = FontWeight.Bold,color=if(color== whitebacground){
           Color.Black
        }
        else{
            whitebacground
        })
    }
    Spacer(Modifier.width(15.dp))
}
@Composable
fun AlertDialogBoxs(){
    /**open the Dialog box*/
    val openDialog = remember{ mutableStateOf(true)}
    /**set Dialog */
    if (openDialog.value){
        AlertDialog(modifier = Modifier.fillMaxWidth()
            .padding(15.dp)
            ,
            onDismissRequest = { openDialog.value = false },
            title = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(25.dp))
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fb),
                        contentDescription = "Android")
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Solution code Android",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )


                }
            },
            text = {
                Text(text = "Hello...! ,\n This is Our AlertDialog Box...",
                    color = Color.White
                )
            },
            confirmButton = {
                TextButton(modifier = Modifier
                    .padding(
                        start = 5.dp,
                        end = 15.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                    .background(
                        color = Color.Gray,
                        shape = CircleShape
                    )
                    ,onClick = {
                        openDialog.value = false
                       // Toast.makeText(c,"Confirm Button is Clicked",Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(text = "Confirm",
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                TextButton(modifier = Modifier
                    .padding(
                        start = 15.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                    .background(
                        color = Color.Red,
                        shape = CircleShape
                    )
                    ,
                    onClick = {
                        openDialog.value = false
                      //  Toast.makeText(c,"Dismiss Button is Clicked",Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(text = "Dismiss",
                        color = Color.White
                    )

                }
            },
            backgroundColor = whitebacground,
            contentColor = Blue300,
            shape = RoundedCornerShape(25.dp)

        )
    }

}
