package com.intern.evtutors.composes.teacherPostInfo.ui

import android.content.ContentValues.TAG
import android.util.Log
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
import com.intern.evtutors.data.models.TeacherPost
import com.miggue.mylogin01.ui.theme.Blue300
import com.miggue.mylogin01.ui.theme.whitebacground

@Composable
fun UIInputInfoRoomTeacher(status:Boolean,teacherPost: TeacherPost,
                           SubjectInput:String,ClassInput:String,
                           outputSubject:(String)->Unit,
                           outputClass: (String) -> Unit,
                           outputForm:(String)->Unit){
    Column(Modifier.fillMaxWidth(0.9f)) {
        var Subject by remember{ mutableStateOf("") }
        var Class by remember{ mutableStateOf("") }

//        Text(text = "CLASS-LIST", color = Color.Gray)
//
//
//        Subject=DropTextFile(SubjectInput,"Choose class ",listOf("Class 1","Class 2","Class 3","Class 4","Class 5"))
//        Spacer(Modifier.height(15.dp))

        Text(text = "SUBJECTS", color = Color.Gray)
        Subject= DropTextFile(SubjectInput,"Choose subjects ",listOf("Toeci","Ielts","English communication","English grammar"))
        Spacer(Modifier.height(15.dp))

        Text(text = "TEACHING FORM", color = Color.Gray)
        outputForm(BoxChooseFromteaching())
        if(status){
            Log.d(TAG,"check Subject "+Subject)
            outputSubject(Subject)
            outputClass(" ")
        }

    }
}
@Composable
fun BoxChooseFromteaching():String{
    var colorChooseOffline by remember{ mutableStateOf(whitebacground) }
    var colorChooseOnline by remember{ mutableStateOf(Blue300) }
    var ChooseOutPut by remember{ mutableStateOf("Online") }
    Row(Modifier.fillMaxWidth()
        .height(40.dp).padding(start = 2.dp, end = 2.dp, top = 2.dp, bottom = 2.dp)
        .background(shape = RoundedCornerShape(10.dp), color = whitebacground)
        .border(width = 1.dp, color = Blue300 ,shape = RoundedCornerShape(15.dp)),

        Arrangement.SpaceAround,
        Alignment.CenterVertically
    ){
        Row(Modifier.height(30.dp)
            .fillMaxWidth(0.45f)
            .clickable {
                colorChooseOffline=Blue300
                colorChooseOnline=whitebacground
                ChooseOutPut="Offline"
            }
            .background(color = colorChooseOffline, shape = RoundedCornerShape(12.dp) ),
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
                ChooseOutPut="Online"
            }
            .
            background(color = colorChooseOnline, shape = RoundedCornerShape(10.dp) ),
            Arrangement.SpaceAround,
            Alignment.CenterVertically

        ) {
            Text(text="Online", fontWeight = FontWeight.Bold, color = colorChooseOffline)
        }
    }
    return ChooseOutPut
}