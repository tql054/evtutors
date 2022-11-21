package com.intern.evtutors.composes.teacherPostInfo.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.cloudinary.transformation.Expression.width
import com.intern.evtutors.R
import com.intern.evtutors.common.Screen.width
import com.intern.evtutors.ui.customer.profile.ui.theme.Purple700
import com.intern.evtutors.ui.customer.profile.ui.theme.whitebacground
import com.miggue.mylogin01.ui.theme.*

import okhttp3.internal.cookieToString

class teacherPost : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatherOfAppsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeTeacherPostInfo("Android",this)
                }
            }
        }
    }
}

@Composable
fun HomeTeacherPostInfo(name: String,context:Context) {
    var itemPage by remember{ mutableStateOf(1) }
    var colorIconPage1 by remember{ mutableStateOf(Color.Gray) }
    var colorIconPage2 by remember{ mutableStateOf(Color.Gray) }
    var colorIconPage3 by remember{ mutableStateOf(Color.Gray) }


    Column(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth().fillMaxHeight(0.20f)
            .background(color = Blue300, shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Box(Modifier.fillMaxWidth(0.8f)){
                Row(Modifier.fillMaxWidth().height(2.dp).background(whitebacground)
                    .align(Alignment.Center)
                ){}
                Row(Modifier.fillMaxWidth(),
                    Arrangement.SpaceBetween,
                    Alignment.CenterVertically
                )  {
                    Row(Modifier.size(25.dp).background(whitebacground,shape = RoundedCornerShape(20.dp))
                           , Arrangement.Center,
                            Alignment.CenterVertically,){
                        Icon(
                            Icons.Sharp.Info, contentDescription = null, Modifier.size(18.dp),
                            Blue300
                        )
                    }
                    Row(Modifier.size(25.dp).background(whitebacground,shape = RoundedCornerShape(20.dp))
                        , Arrangement.Center,
                        Alignment.CenterVertically,){
                        Icon(
                            painter= painterResource(R.drawable.icon_schedules), contentDescription = null, Modifier.size(15.dp),
                            colorIconPage1
                        )
                    }
                    Row(Modifier.size(25.dp).background(whitebacground,shape = RoundedCornerShape(20.dp))
                        , Arrangement.Center,
                        Alignment.CenterVertically,){
                        Icon(
                            Icons.Sharp.Person, contentDescription = null, Modifier.size(18.dp),
                            colorIconPage2
                        )
                    }
                    Row(Modifier.size(25.dp).background(whitebacground,shape = RoundedCornerShape(20.dp))
                        , Arrangement.Center,
                        Alignment.CenterVertically,){
                        Icon(
                            Icons.Sharp.Check, contentDescription = null, Modifier.size(18.dp),
                            colorIconPage3
                        )
                    }


                }
            }
        }
        Column(Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(top = 15.dp)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment=Alignment.CenterHorizontally
            ) {
            when(itemPage){
                0->{
                    colorIconPage1= Grayy100
                    colorIconPage2= Grayy100
                    colorIconPage3= Grayy100
                    UIInputInfoRoomTeacher()
                }
                1->{
                    colorIconPage1= Blue300
                    colorIconPage2= Grayy100
                    colorIconPage3= Grayy100
                    UIInputTimePostTeacher(context)
                }
                2->{
                    colorIconPage1= Blue300
                    colorIconPage2= Blue300
                    colorIconPage3= Grayy100
                    UIInputInfoTeacher()
                }
                3->{
                    colorIconPage1= Blue300
                    colorIconPage2= Blue300
                    colorIconPage3= Blue300
                }
            }
        }

        Row(Modifier.fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),

            Arrangement.SpaceAround,
            Alignment.CenterVertically

        ){
            Row(Modifier.width(120.dp)
                .fillMaxHeight()
                .background(whitebacground,shape = RoundedCornerShape(5.dp))
                .clickable {
                           if(itemPage>0){
                               itemPage -= 1
                           }
                },
                Arrangement.SpaceAround,
                Alignment.CenterVertically

            ){
                Text(text = "Quit", fontWeight = FontWeight.Bold, color = Color.Gray)
            }
            Row(Modifier.width(120.dp)
                .fillMaxHeight()
                .background(Blue300,shape = RoundedCornerShape(5.dp))
                .clickable {
                           if(itemPage<3){
                               itemPage += 1
                           }
                },
                Arrangement.SpaceAround,
                Alignment.CenterVertically

            ){
                Text(text = "Next", fontWeight = FontWeight.Bold, color = whitebacground)
            }

        }
    }
}




@Composable
fun UIReviewInfoInput(){

}
@Composable
fun DropTextFile(lable:String,listinfo:List<String>){
    var dropdownStatus by remember{ mutableStateOf(false) }

    var list=listinfo
        //listOf("Class 1","Class 2","Class 3","Class 4","Class 5")
    var selectedItem by remember{ mutableStateOf("") }
    var textFileSize by remember{ mutableStateOf(Size.Zero) }

    var icon =if(dropdownStatus){
        Icons.Filled.KeyboardArrowUp
    }else{

        Icons.Filled.KeyboardArrowDown
    }
    Box(Modifier.fillMaxWidth()){
        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { layoutCoordinates ->
                        textFileSize=layoutCoordinates.size.toSize()
                    },
                value = selectedItem,
                onValueChange ={selectedItem = it},
                label = { Text(text = lable)},
                colors= TextFieldDefaults.outlinedTextFieldColors(textColor = BlackText),
                singleLine = true,
                trailingIcon = {
                    Icon(imageVector =  icon,
                        contentDescription ="choose class Toggle",
                        Modifier.clickable {dropdownStatus=!dropdownStatus  } )
                }
            )
            DropdownMenu(
                expanded = dropdownStatus,
                onDismissRequest = {dropdownStatus=false},
                modifier = Modifier
                    .width(with(LocalDensity.current){textFileSize.width.toDp()})

            ){
                list.forEach{
                        lable ->
                    DropdownMenuItem(onClick = {
                        selectedItem=lable
                        dropdownStatus=false
                    }){
                        Text(text = lable)
                    }
                }
            }
        }
    }



}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FatherOfAppsTheme {
       // HomeTeacherPostInfo("Android")

    }
}