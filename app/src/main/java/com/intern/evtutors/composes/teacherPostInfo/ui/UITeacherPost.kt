package com.intern.evtutors.composes.teacherPostInfo.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.cloudinary.transformation.Expression.width
import com.intern.evtutors.R
import com.intern.evtutors.common.Screen.width
import com.intern.evtutors.data.models.TeacherPost
import com.intern.evtutors.data.models.TimePost
import com.intern.evtutors.view_models.TeacherPostViewModel
import com.miggue.mylogin01.ui.theme.*

import okhttp3.internal.cookieToString

class UITeacherPost : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatherOfAppsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeTeacherPostInfo()
                }
            }
        }
    }
}

@Composable
fun HomeTeacherPostInfo(teacherPostViewModel: TeacherPostViewModel= hiltViewModel()) {

    var itemPage by remember{ mutableStateOf(0) }
    var doing by remember{ mutableStateOf(10) }
    var StatusChane by remember{ mutableStateOf(false) }
    var colorIconPage1 by remember{ mutableStateOf(Color.Gray) }
    var colorIconPage2 by remember{ mutableStateOf(Color.Gray) }
    var colorIconPage3 by remember{ mutableStateOf(Color.Gray) }
    var FullAdress by mutableStateOf(teacherPostViewModel.NameAdress+", "+teacherPostViewModel.NameDistrict+"," +teacherPostViewModel.NameCity)

    var timePost= TimePost(1,"","","")
    var time: MutableSet<TimePost> = mutableSetOf(timePost)

    var PostInput by remember{ mutableStateOf(TeacherPost("","",0,
        time,"","","",""
    )) }
    val calendarStart = Calendar.getInstance()

    PostInput.Phone=teacherPostViewModel.Numberphone
    PostInput.Adress=FullAdress
    PostInput.Startday = calendarStart[Calendar.DATE].toString()+"-"+(calendarStart[Calendar.MONTH]+1).toString()+"-"+calendarStart[Calendar.YEAR].toString()
    var Subject by remember{ mutableStateOf("") }
    var Class by remember{ mutableStateOf("") }
    PostInput.Subjects=Subject

    when(doing){
        0->{
            StatusChane=true
            if(PostInput.Subjects!=""){
                Log.d(TAG, "ktra post $PostInput")
                itemPage += 1
                StatusChane=false
                doing=10
            }

        }
        1->{
            StatusChane=true
            if(PostInput.ShiftsCount!=0){
                itemPage += 1
                StatusChane=false
                doing=10
            }

        }
    }


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

                    UIInputInfoRoomTeacher(StatusChane,PostInput,Subject,Class,outputSubject={Subject=it}
                        ,outputClass={Class=it}
                        , outputForm={PostInput.Form=it})

                }
                1->{
                    colorIconPage1= Blue300
                    colorIconPage2= Grayy100
                    colorIconPage3= Grayy100

                    UIInputTimePostTeacher(onchaneCount = {
                        PostInput.ShiftsCount=it
                    })
                }
                2->{
                    colorIconPage1= Blue300
                    colorIconPage2= Blue300
                    colorIconPage3= Grayy100
                    StatusChane=false
                    UIInputInfoTeacher()
                }
                3->{
                    colorIconPage1= Blue300
                    colorIconPage2= Blue300
                    colorIconPage3= Blue300
                    StatusChane=false
                    UIConfirmInfo(
                        PostInput
//                        TeacherPost("English communication","Online",2,
//                            time,"11/28/2022","0912345612","tieen ha","Vo Quang Tan"
//                        )
                    )
                }
            }
        }

        Row(Modifier.fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),

            Arrangement.SpaceAround,
            Alignment.CenterVertically

        ){
            Row(Modifier.width(150.dp)
                .fillMaxHeight()
                .background(Grayy100,shape = RoundedCornerShape(5.dp))
                .clickable {
                           if(itemPage>0){
                               itemPage -= 1
                               StatusChane=true
                           }
                },
                Arrangement.SpaceAround,
                Alignment.CenterVertically

            ){
                Text(text = "Quit", fontWeight = FontWeight.Bold, color = Color.Gray)
            }
            Row(Modifier.width(150.dp)
                .fillMaxHeight()
                .background(Blue300,shape = RoundedCornerShape(5.dp))
                .clickable {
                    when(itemPage){
                        0->{
                            doing=0
                        }
                        1->{
                            doing=1

                        }
                        2->{
                            itemPage += 1
                        }
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
fun DropTextFile(text:String,lable:String,listinfo:List<String>):String{

    var dropdownStatus by remember{ mutableStateOf(false) }
    var checkClick by remember{ mutableStateOf(true) }
    var list=listinfo
    var selectedItem by remember{ mutableStateOf("") }
    var textFileSize by remember{ mutableStateOf(Size.Zero) }

    if(text.isNotEmpty() && checkClick ){
        selectedItem=text
    }
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
                onValueChange ={
                    checkClick=false
                    selectedItem = it},
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
                        checkClick=false
                        selectedItem=lable
                        dropdownStatus=false
                    }){
                        Text(text = lable)
                    }
                }
            }
        }
    }
return selectedItem
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FatherOfAppsTheme {
       // HomeTeacherPostInfo("Android")

    }
}