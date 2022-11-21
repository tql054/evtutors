package com.intern.evtutors.composes.daglo

import android.content.Context
import android.graphics.Color.green
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intern.evtutors.R
import com.intern.evtutors.ui.customer.profile.ui.theme.BlueText
import com.intern.evtutors.ui.customer.profile.ui.theme.GrayText
import com.intern.evtutors.ui.customer.profile.ui.theme.whitebacground
import com.miggue.mylogin01.ui.theme.BlackText
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme

class loginGoogle : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatherOfAppsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    /**call the dialog box*/
                    var visible by remember{ mutableStateOf(false) }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement= Arrangement.Center
                    ) {
                        Button(onClick = { visible = !visible },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Click Me")

                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        if (visible){
//                            AlertDialogBoxs()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogBoxs(
    onCheckRegister:(String)-> Unit
){
    /**open the Dialog box*/
    val openDialog = remember{ mutableStateOf(true)}
    /**set Dialog */
    if (openDialog.value){
        AlertDialog(modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
            ,
            onDismissRequest = { openDialog.value = true },
            title = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)

                ){
                    Text(
                        text = "Register As a ?",
                        color= BlueText,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            },
            text = {
                Text(text = "Please choose",
                    color = Color.Gray
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
                        color = BlueText,
                        shape = CircleShape
                    )
                    ,onClick = {
                        onCheckRegister("Student")
                        openDialog.value = false
                    }
                ) {
                    Text(text = "Student",
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                TextButton(modifier = Modifier
                    .padding(
                        start = 5.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                    .background(
                        color = Color(0xFFCECBCB),
                        shape = CircleShape
                    )
                    ,
                    onClick = {
                        onCheckRegister("Teacher")
                        openDialog.value = false

                    }
                ) {
                    Text(text = "Teacher",
                        color = BlueText
                    )

                }
            },
            backgroundColor = whitebacground,
            contentColor = Color.Green,
            shape = RoundedCornerShape(25.dp)

        )
    }

}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    FatherOfAppsTheme {
//
//        AlertDialogBoxs( )
//    }
//}