package com.intern.evtutors.composes.teacherPostInfo.ui

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.view_models.TeacherPostViewModel
import com.miggue.mylogin01.ui.theme.*


@Composable
fun UIInputInfoTeacher(teacherPostViewModel: TeacherPostViewModel= hiltViewModel()){
    var Numberphone by remember{ mutableStateOf("") }

    var checkchanePhone by remember{ mutableStateOf(true) }
    var checkchaneAdress by remember{ mutableStateOf(true) }
    var Adress by remember{ mutableStateOf("") }
    var color by remember{ mutableStateOf(Gray200) }
    var Numberphoneinput =teacherPostViewModel.Numberphone

    if(teacherPostViewModel.NameAdress!=""&&checkchaneAdress){
        Numberphone=Numberphoneinput
    }
    if(Numberphoneinput!=""&&checkchanePhone){
        Numberphone=Numberphoneinput
    }

    if(validatenumberPhone(Numberphone)){
        color= Color.Red
    }else{
        teacherPostViewModel.Numberphone=Numberphone
        color= Blue300
    }
    teacherPostViewModel.NameAdress=Adress
    Column(Modifier.fillMaxWidth(0.9f).fillMaxHeight()) {
        Text(text = "Number Phone", color = Grayy100)
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = Numberphone,
            onValueChange ={
                checkchanePhone=false
                Numberphone = it},
            label = { Text(text = "number phone") },
            colors= TextFieldDefaults.outlinedTextFieldColors(textColor = BlackText),
            textStyle = TextStyle(fontSize = 15.sp, color =color),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector =  Icons.Filled.Phone,
                    contentDescription ="choose class Toggle",tint=color
                )
            }
        )
        Spacer(Modifier.height(25.dp))
        Box(Modifier.fillMaxWidth().height(360.dp).padding(top = 10.dp)){


            Column(
                Modifier.fillMaxWidth().height(350.dp).padding(top = 15.dp)
                    .border(width=1.dp, color = Grayy100, shape = RoundedCornerShape(10.dp) )
            ) {
               // Text(text="Your ADRESS", fontSize = 18.sp, color = Blue300)
                Spacer(Modifier.height(25.dp))
                Column(
                    Modifier.fillMaxSize()
                        .padding(start = 10.dp,end=5.dp)) {
                    Text(text="Province")
                    teacherPostViewModel.NameCity=DropTextFile(teacherPostViewModel.NameCity,"Province or city",listOf("DangNangCity","HoChiMinhCity","HaNoiCity"))
                    Text(text="District")
                    teacherPostViewModel.NameDistrict=DropTextFile(teacherPostViewModel.NameDistrict,"District",listOf("HaiChau","Lienchieu","HoaKhanh"))
                    Text(text="Adress")
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = Adress,
                        onValueChange ={
                            checkchaneAdress=false
                            Adress = it},
                        label = { Text(text = "your adress") },
                        colors= TextFieldDefaults.outlinedTextFieldColors(textColor = BlackText),
                        singleLine = true,
                    )

                }



            }
            Row(Modifier.padding(start = 10.dp)) {
                Row(Modifier.background(whitebacground)) {
                    Text(text="Your ADRESS", fontSize = 18.sp, color = Blue300) } }

        }


    }

}
fun validatenumberPhone(numberPhone:String):Boolean{

    if(numberPhone.length!=10 || !numberPhone.matches("[0-9]{10}$".toRegex())){
        return true
    }
    return false
}