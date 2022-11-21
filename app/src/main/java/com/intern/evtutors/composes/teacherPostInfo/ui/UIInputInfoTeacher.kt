package com.intern.evtutors.composes.teacherPostInfo.ui

import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miggue.mylogin01.ui.theme.BlackText
import com.miggue.mylogin01.ui.theme.Blue300
import com.miggue.mylogin01.ui.theme.Grayy100


@Composable
fun UIInputInfoTeacher(){
    var Numberphone by remember{ mutableStateOf("") }
    Column(Modifier.fillMaxWidth(0.9f).fillMaxHeight()) {
        Text(text = "Number Phone", color = Grayy100)
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = Numberphone,
            onValueChange ={Numberphone = it},
            label = { Text(text = "number phone") },
            colors= TextFieldDefaults.outlinedTextFieldColors(textColor = BlackText),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector =  Icons.Filled.Phone,
                    contentDescription ="choose class Toggle",
                )
            }
        )
        Spacer(Modifier.height(25.dp))
        Column(
            Modifier.fillMaxWidth().height(400.dp).padding(top = 15.dp)
                .border(width=1.dp, color = Grayy100, shape = RoundedCornerShape(10.dp) )
        ) {
            Text(text="Your ADRESS", fontSize = 18.sp, color = Blue300)
            Column(
                Modifier.fillMaxSize()
                .padding(start = 5.dp)) {
                Text(text="Province")
                DropTextFile("DangNangCity",listOf("DangNangCity","HoChiMinhCity","HaNoiCity"))
                Text(text="District")
                DropTextFile("HaiChau",listOf("HaiChau","Lienchieu","HoaKhanh"))
                Text(text="Adress")
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = Numberphone,
                    onValueChange ={Numberphone = it},
                    label = { Text(text = "your adress") },
                    colors= TextFieldDefaults.outlinedTextFieldColors(textColor = BlackText),
                    singleLine = true,
                )

            }



        }

    }
}