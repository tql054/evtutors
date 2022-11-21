package com.intern.evtutors.composes.teacherPostInfo.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.sharp.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intern.evtutors.ui.customer.profile.ui.theme.whitebacground
import com.miggue.mylogin01.ui.theme.Blue300
import com.miggue.mylogin01.ui.theme.Brown300
import java.util.*


@Composable
fun UIInputTimePostTeacher(context: Context){
    var colorIcon1 by remember{ mutableStateOf(whitebacground) }
    var colorIcon2 by remember{ mutableStateOf(whitebacground) }
    var colorIcon3 by remember{ mutableStateOf(whitebacground) }
    var colorIcon4 by remember{ mutableStateOf(whitebacground) }
    var colorIcon5 by remember{ mutableStateOf(whitebacground) }
    var colorIcon6 by remember{ mutableStateOf(whitebacground) }
    var colorIcon7 by remember{ mutableStateOf(whitebacground) }
    var colorStatus by remember{ mutableStateOf(0) }


    Column(Modifier.fillMaxWidth(0.9f)) {
        Text(text = "NUMBER OF LESSONS PER WEEK", color = Color.Gray)
        when(colorStatus){
            1->{
                colorIcon1= Blue300
                colorIcon2=whitebacground
                colorIcon3=whitebacground
                colorIcon4=whitebacground
                colorIcon5=whitebacground
                colorIcon6=whitebacground
                colorIcon7=whitebacground
            }
            2->{
                colorIcon1=whitebacground
                colorIcon2= Blue300
                colorIcon3=whitebacground
                colorIcon4=whitebacground
                colorIcon5=whitebacground
                colorIcon6=whitebacground
                colorIcon7=whitebacground
            }
            3->{
                colorIcon1=whitebacground
                colorIcon2=whitebacground
                colorIcon3=Blue300
                colorIcon4=whitebacground
                colorIcon5=whitebacground
                colorIcon6=whitebacground
                colorIcon7=whitebacground
            }
            4->{
                colorIcon1=whitebacground
                colorIcon2=whitebacground
                colorIcon3=whitebacground
                colorIcon4=Blue300
                colorIcon5=whitebacground
                colorIcon6=whitebacground
                colorIcon7=whitebacground
            }
            5->{
                colorIcon1=whitebacground
                colorIcon2=whitebacground
                colorIcon3=whitebacground
                colorIcon4=whitebacground
                colorIcon5=Blue300
                colorIcon6=whitebacground
                colorIcon7=whitebacground
            }
            6->{
                colorIcon1=whitebacground
                colorIcon2= whitebacground
                colorIcon3=whitebacground
                colorIcon4=whitebacground
                colorIcon5=whitebacground
                colorIcon6=Blue300
                colorIcon7=whitebacground
            }
            7->{
                colorIcon1=whitebacground
                colorIcon2= whitebacground
                colorIcon3=whitebacground
                colorIcon4=whitebacground
                colorIcon5=whitebacground
                colorIcon6=whitebacground
                colorIcon7=Blue300
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            Arrangement.Center){
            itemDate("1 Shifts",colorIcon1, onClick = {
                colorStatus=1
            })
            itemDate("2 Shifts",colorIcon2,onClick = {
                colorStatus=2
            })
            itemDate("3 Shifts",colorIcon3,onClick = {
                colorStatus=3
            })
            itemDate("4 Shifts",colorIcon4,onClick = {
                colorStatus=4
            })

        }
        Spacer(Modifier.height(10.dp))
        Row(
            Modifier.fillMaxWidth(),
            Arrangement.Center){
            itemDate("5 Shifts",colorIcon5,onClick = {
                colorStatus=5
            })
            itemDate("6 Shifts",colorIcon6,onClick = {
                colorStatus=6
            })
            itemDate("7 Shifts",colorIcon7,onClick = {
                colorStatus=7
            })
        }
        Spacer(Modifier.height(20.dp))
        Text(text = "CREATE A CLASS SCHDULE", color = Color.Gray)
        itemTimeInDay("Monday", context)
        itemTimeInDay("Tuesday", context)
        itemTimeInDay("Wednesday", context)
        itemTimeInDay("Thusday", context)
        itemTimeInDay("Friday", context)
        itemTimeInDay("Saturday", context)
        itemTimeInDay("Sunday", context)

    }
}
@Composable
fun itemTimeInDay(nameDay:String,context: Context){
    var numberSelect by remember{ mutableStateOf(0) }
    Column(
        Modifier.fillMaxWidth().heightIn(125.dp,250.dp).wrapContentHeight()
            .padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 10.dp)
            .border(width = 1.dp, color = Brown300, shape = RoundedCornerShape(10.dp))
    ) {
        Text(text = nameDay, color = Blue300, fontSize = 18.sp)
        Spacer(Modifier.height(10.dp))
        Column(Modifier.fillMaxWidth()
             ,
        ) {
            for(i in 1..numberSelect){
                ItemHoursShifts(context)
                Spacer(Modifier.height(2.dp))
            }
           // ItemHoursShifts()
//            ItemHoursShifts()
//            ItemHoursShifts()
        }

        Row(Modifier.fillMaxWidth().padding(end = 5.dp, bottom = 5.dp),
            Arrangement.End) {
            Row(Modifier.height(25.dp)
                .clickable { if(numberSelect<5){numberSelect+=1} }
                .width(65.dp).background(Blue300, shape = RoundedCornerShape(20.dp)),
                Arrangement.SpaceAround,
                Alignment.CenterVertically
            ) {
                Text(text = "ADD", color = whitebacground)
            }
        }
    }
}
@Composable
fun ItemHoursShifts(context:Context){
    var calendarStart = Calendar.getInstance()
    var calendarEnd = Calendar.getInstance()

    var timeStartHours =calendarStart[Calendar.HOUR_OF_DAY]
    var timeStartMinute =calendarStart[Calendar.MINUTE]
    var timeStrart by remember{ mutableStateOf("") }

    var timeEndHours =calendarEnd.get(Calendar.HOUR)
    var timeEndMinute =calendarEnd.get(Calendar.MINUTE)
    var timeEnd by remember{ mutableStateOf("") }

    var TimePickerDialogStart= TimePickerDialog(
        context,
        {_, timeStartHours : Int, timeStartMinute: Int ->
            timeStrart="$timeStartHours:$timeStartMinute"
        }, timeStartHours, timeStartMinute,  true
    )
    var TimePickerDialogEnd= TimePickerDialog(
        context,
        {_, timeEndHours : Int, timeEndMinute: Int ->
            timeEnd="$timeEndHours:$timeEndMinute"
        }, timeEndHours, timeEndMinute,  true
    )

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
            Text(text =timeStrart.toString())
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
            Text(text = timeEnd)
        }
        Row(Modifier.height(30.dp).width(50.dp)
            .clickable {

            },
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ){
            Icon(
                Icons.Filled.Close, contentDescription = null, Modifier.size(18.dp),
                Color.Red
            )
        }


    }
}
@Composable
fun itemDate(number:String,color: Color,
            onClick:()->Unit){
    Row(Modifier.width(70.dp).height(30.dp).background(color,shape = RoundedCornerShape(5.dp))
        .clickable {
            onClick()
        }
        .border(width = 1.dp, color = Color.Gray ,shape = RoundedCornerShape(5.dp)),
        Arrangement.SpaceAround,
        Alignment.CenterVertically

    ){
        Text(text = number,color=if(color== whitebacground){
            Blue300
        }
        else{
            whitebacground
        })
    }
    Spacer(Modifier.width(15.dp))
}

