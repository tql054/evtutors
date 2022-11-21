package com.intern.evtutors.composes.teacherPostInfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.intern.evtutors.ui.customer.profile.ui.theme.whitebacground
import com.miggue.mylogin01.ui.theme.Blue300

@Composable
fun UIInputInfoRoomTeacher(){
    Column(Modifier.fillMaxWidth(0.9f)) {
        Text(text = "CLASS-LIST", color = Color.Gray)

        DropTextFile("Choose class ",listOf("Class 1","Class 2","Class 3","Class 4","Class 5"))
        Spacer(Modifier.height(15.dp))
        Text(text = "TEACHING FROM", color = Color.Gray)
        BoxChooseFromteaching()
    }
}
@Composable
fun BoxChooseFromteaching(){
    var colorChooseOffline by remember{ mutableStateOf(whitebacground) }
    var colorChooseOnline by remember{ mutableStateOf(Blue300) }
    Row(Modifier.fillMaxWidth()
        .height(40.dp).padding(start = 5.dp, end = 5.dp, top = 2.dp, bottom = 2.dp)
        .background(shape = RoundedCornerShape(5.dp), color = whitebacground)
        .border(width = 1.dp, color = Blue300 ,shape = RoundedCornerShape(5.dp)),

        Arrangement.SpaceAround,
        Alignment.CenterVertically
    ){
        Row(Modifier.height(30.dp)
            .fillMaxWidth(0.45f)
            .clickable {
                colorChooseOffline=Blue300
                colorChooseOnline=whitebacground
            }
            .background(color = colorChooseOffline, shape = RoundedCornerShape(5.dp) ),
            Arrangement.SpaceAround,
            Alignment.CenterVertically

        ) {
            Text(text="Offline", fontWeight = FontWeight.Bold, color = colorChooseOnline)
        }
        Row(Modifier.height(30.dp)
            .fillMaxWidth(0.90f)
            .clickable {
                colorChooseOffline= whitebacground
                colorChooseOnline= Blue300
            }
            .
            background(color = colorChooseOnline, shape = RoundedCornerShape(5.dp) ),
            Arrangement.SpaceAround,
            Alignment.CenterVertically

        ) {
            Text(text="Online", fontWeight = FontWeight.Bold, color = colorChooseOffline)
        }
    }
}