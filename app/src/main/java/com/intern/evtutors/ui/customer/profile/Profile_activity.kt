package com.intern.evtutors.ui.customer.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.intern.evtutors.activities.Login
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.models.Role
import com.intern.evtutors.data.models.User
import com.intern.evtutors.ui.customer.login.LoginViewModel
import com.intern.evtutors.ui.customer.profile.ui.theme.*
import com.miggue.mylogin01.ui.theme.SecondaryColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Profile_activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatherOfAppsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Profile_Greeting()
                }
            }
        }
    }
}

@Composable
 fun Profile_Greeting(
    navHostController: NavHostController,
    ProfileViewModel  : ProfileViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel= hiltViewModel())
 {
    var user = CustomerEntity(0,0,"",0,"","","","","","","",0)
    var role: MutableSet<Role> = mutableSetOf()
    var userupdate: User = User(0,"",0,"","","","","","", password = null,role = role)
    val scope = CoroutineScope(Dispatchers.IO + Job())
    val context = LocalContext.current
    val age = remember { mutableStateOf("0") }

    val EdittextStatusName = remember { mutableStateOf(false) }
    val EdittextStatusadress = remember { mutableStateOf(false) }
    val EdittextStatusAge = remember { mutableStateOf(false) }
    val EdittextStatusGender = remember { mutableStateOf(false) }
    val StatusBottum = remember { mutableStateOf(true) }
    val EdittextStatusGmail = remember { mutableStateOf(false) }
    val EdittextStatusPhone = remember { mutableStateOf(false) }
    val EdittextStatuspasswprd = remember { mutableStateOf(false) }
    val update = remember { mutableStateOf(false) }

    var myuser = remember { mutableStateOf(user) }
    var myuserUpdate = remember { mutableStateOf(userupdate) }

    var myuserCheck = remember { mutableStateOf(myuserUpdate.value) }

    ProfileViewModel.getuser()
    myuser.value= ProfileViewModel.myuser

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement =Arrangement.Top,
        horizontalAlignment =Alignment.CenterHorizontally,
        ){

        StatusBottum.value= header(name=myuser.value.name, gmail = myuser.value.email)

        if( StatusBottum.value){
            Row(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Person, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Name",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusName.value){
                            myuserUpdate.value.name = NameOutlinedTextField()

                        }else{
                            Text(myuser.value.name)
                        }
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.CenterVertically
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        TextButton(onClick = {
                            if(EdittextStatusName.value && myuserUpdate.value.name.isNotEmpty() ){
                                myuserUpdate.value.password=null
                                loginViewModel.UpdateAccount(myuser.value.id,myuserUpdate.value)
                                myuserCheck.value=loginViewModel.myuserupdate
                                if(myuserCheck.value.id != 0){
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                            EdittextStatusName.value=!EdittextStatusName.value

                        }) {
                            if(!EdittextStatusName.value){
                                Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                            }else{
                                Text(text = "Save",fontSize = 16.sp, color = BlueText)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Email, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Gmail",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusGmail.value){
                            myuserUpdate.value.email = EmailOutlinedTextField()

                        }else{
                            Text(myuser.value.email)
                        }
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.CenterVertically
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        TextButton(onClick = {
                            if(EdittextStatusGmail.value && myuserUpdate.value.email.isNotEmpty()){
                                myuserUpdate.value.password=null
                                loginViewModel.UpdateAccount(myuser.value.id,myuserUpdate.value)
                                myuserCheck.value=loginViewModel.myuserupdate
                                if(myuserCheck.value.id != 0){
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                            EdittextStatusGmail.value=!EdittextStatusGmail.value

                        }) {
                            if(!EdittextStatusGmail.value){
                                Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                            }else{
                                Text(text = "Save",fontSize = 16.sp, color = BlueText)
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Lock, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Password",fontSize = 18.sp)
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(!EdittextStatuspasswprd.value){
                            Text(text = "Pass word",fontSize = 13.sp)
                        }else{
                            var a= OutlinedTextField("pass word","pass word")
                            var b= OutlinedTextField("pass word","pass word")
                        }

                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.CenterVertically
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        TextButton(onClick = {
                            EdittextStatuspasswprd.value=!EdittextStatuspasswprd.value
                        }) {
                            if(!EdittextStatuspasswprd.value){
                                Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                            }
                            else{
                                Text(text = "Save",fontSize = 16.sp, color = BlueText)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Phone, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Phone Number",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusPhone.value){
                            myuserUpdate.value.phone = PhoneOutlinedTextField()

                        }else{
                            Text(myuser.value.phone)
                        }
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.CenterVertically
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        TextButton(onClick = {
                            if(EdittextStatusPhone.value && myuserUpdate.value.email.isNotEmpty()){
                                myuserUpdate.value.password=null
                                loginViewModel.UpdateAccount(myuser.value.id,myuserUpdate.value)
                                myuserCheck.value=loginViewModel.myuserupdate
                                if(myuserCheck.value.id != 0){
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                            EdittextStatusPhone.value=!EdittextStatusPhone.value

                        }) {
                            if(!EdittextStatusPhone.value){
                                Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                            }else{
                                Text(text = "Save",fontSize = 16.sp, color = BlueText)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Email, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Payment",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        Text(text = "Payment",fontSize = 13.sp)
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.CenterVertically
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        TextButton(onClick = {}) {
                            Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                        }
                    }
                }
            }

            if(myuser.value.roleID==3){
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        Row(modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                        ){
                            Icon(Icons.Sharp.Favorite, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                            Text(text = "My certificates",fontSize = 18.sp)
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp)){
                            Text(text = "Payment",fontSize = 13.sp)
                        }
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        ,
                        horizontalArrangement = Arrangement.End,
                        //verticalAlignment = Alignment.CenterVertically
                    ){
                        Column(modifier = Modifier
                            .fillMaxWidth(0.7f)
                        ){
                            TextButton(onClick = {
                                navHostController.navigate("home/profile/certificates")
                            }) {
                                Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                            }
                        }
                    }
                }
            }
        }else{
            Row(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Person, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Age",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusAge.value){

                            age.value = AgeOutlinedTextField()
                                myuserUpdate.value.age=age.value.toInt()
                        }else{
                            Text(myuser.value.age.toString())
                        }
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.CenterVertically
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        TextButton(onClick = {
                            if(EdittextStatusAge.value && myuserUpdate.value.age !=0 ){
                                myuserUpdate.value.password=null
                                loginViewModel.UpdateAccount(myuser.value.id,myuserUpdate.value)
                                myuserCheck.value=loginViewModel.myuserupdate
                                if(myuserCheck.value.id != 0){
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                            EdittextStatusAge.value=!EdittextStatusAge.value

                        }) {
                            if(!EdittextStatusAge.value){
                                Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                            }else{
                                Text(text = "Save",fontSize = 16.sp, color = BlueText)
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Person, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Gender",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusGender.value){
                            myuserUpdate.value.gender = GenderOutlinedTextField()

                        }else{
                            Text(myuser.value.gender)
                        }
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.CenterVertically
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        TextButton(onClick = {
                            if(EdittextStatusGender.value && myuserUpdate.value.gender.isNotEmpty() ){
                                myuserUpdate.value.password=null
                                loginViewModel.UpdateAccount(myuser.value.id,myuserUpdate.value)
                                myuserCheck.value=loginViewModel.myuserupdate
                                if(myuserCheck.value.id != 0){
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                            EdittextStatusGender.value=!EdittextStatusGender.value

                        }) {
                            if(!EdittextStatusGender.value){
                                Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                            }else{
                                Text(text = "Save",fontSize = 16.sp, color = BlueText)
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 15.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Person, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Adress",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusadress.value){
                            myuserUpdate.value.address = adressOutlinedTextField()

                        }else{
                            Text(myuser.value.address)
                        }
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.CenterVertically
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        TextButton(onClick = {
                            if(EdittextStatusadress.value && myuserUpdate.value.address.isNotEmpty() ){
                                myuserUpdate.value.password=null
                                loginViewModel.UpdateAccount(myuser.value.id,myuserUpdate.value)
                                myuserCheck.value=loginViewModel.myuserupdate
                                if(myuserCheck.value.id != 0){
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                            EdittextStatusadress.value=!EdittextStatusadress.value

                        }) {
                            if(!EdittextStatusadress.value){
                                Text(text = "Edit",fontSize = 16.sp, color = GrayText)
                            }else{
                                Text(text = "Save",fontSize = 16.sp, color = BlueText)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))

        }



        Spacer(modifier = Modifier.height(45.dp))
        Button(modifier = Modifier.height(45.dp)
            .fillMaxWidth(0.7f)
            .background(Color.White),
        onClick = {

        },
            shape = RoundedCornerShape(15),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border =  BorderStroke(1.dp, color = BlueText)
        ){
            Text(text = "Become Premium", color = BlueText )
        }
        Spacer(modifier = Modifier.height(35.dp))
        TextButton(onClick = {
            var intent: Intent = Intent(context, Login::class.java)
                        context.startActivity(intent)
        }) {
            Text(text = "LOG OUT",fontSize = 30.sp, color = BlueText)
        }
    }
}

@Composable
 fun header( name :String, gmail:String):Boolean{

   // val how = remember { mutableStateOf(user) }
    val scope = CoroutineScope(Dispatchers.IO + Job())
    var username = remember { mutableStateOf("") }
    val StatusBottumHD = remember { mutableStateOf(true) }




        Column(modifier = Modifier
            .fillMaxWidth()
            .background(SecondaryColor)
            .fillMaxHeight(0.40f),
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(25.dp)
                ,
                Alignment.Center,
            ){
                Column(
                    horizontalAlignment =Alignment.CenterHorizontally,
                ) {

                    Image(painter = painterResource(id = com.intern.evtutors.R.drawable.onboar1), contentDescription = "",
                        modifier = Modifier.size(120.dp).clip(CircleShape).clickable {  },contentScale = ContentScale.FillBounds
                    )
                    Text(text = name, fontSize = 25.sp, color = Color.Black)
                    Text(text =gmail, fontSize = 15.sp, color = Color.Black)

                    Spacer(modifier = Modifier.height(30.dp))
                    Row(modifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth(0.9f).background(Color.White),
                        horizontalArrangement =Arrangement.SpaceEvenly
                    ){
                        if( StatusBottumHD.value ){
                            Button(onClick = {},
                                modifier = Modifier
                                    .height(45.dp)
                                    //.width(130.dp)
                                    .weight(0.4f)

                                ,
                                shape = RoundedCornerShape(15),
//
                            ){
                                Text(text = "Advanced", color = Color.White)
                            }
                        }else{
                            Button(onClick = {StatusBottumHD.value= !StatusBottumHD.value},
                                modifier = Modifier
                                    .height(45.dp)
                                    //.width(130.dp)
                                    .weight(0.4f)


                                ,
                                shape = RoundedCornerShape(15,),
                                border =  BorderStroke(1.dp, color = BlueText),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                            ){
                                Text(text = "Advanced", color = Color.Black)
                            }
                        }

                        Box(modifier = Modifier.weight(0.05f))
                        if( !StatusBottumHD.value ){
                            Button(onClick = {},
                                modifier = Modifier
                                    .height(45.dp)
                                    //.width(130.dp)
                                    .weight(0.4f)

                                ,
                                shape = RoundedCornerShape(15),
//
                            ){
                                Text(text = "Basic", color = Color.White)
                            }
                        }else{
                            Button(onClick = {StatusBottumHD.value= !StatusBottumHD.value},
                                modifier = Modifier
                                    .height(45.dp)
                                    //.width(130.dp)
                                    .weight(0.4f)


                                ,
                                shape = RoundedCornerShape(15),
                                border =  BorderStroke(1.dp, color = BlueText),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                            ){
                                Text(text = "Basic", color = Color.Black)
                            }
                        }


//                Button(onClick = {},
//                    modifier = Modifier
//                        .background(Color.Blue),){
//                    Text(text = "Badges",color = Color.White)
//                }
                    }
                }
            }
        }
    return StatusBottumHD.value

}
@Composable
fun Text(name: String){
//    if(name?.isEmpty())
//    {
//        name = "NUll"
//    }
    Text(text = name,fontSize = 13.sp)
}

@Composable
fun NameOutlinedTextField():String{


    var username = remember { mutableStateOf("") }



    OutlinedTextField(
        value = username.value,
        onValueChange = { username.value = it },
        label = { Text(text = "username", fontSize = 11.sp) },
        placeholder = { Text(text = "username",fontSize = 12.sp) },
        textStyle = TextStyle(fontSize = 12.sp),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f)

            .height(55.dp)
            .padding(bottom = 0.dp)
            .defaultMinSize(minHeight = 0 .dp),

    )
    return username.value
}

@Composable
fun EmailOutlinedTextField():String{

    var gmail = remember { mutableStateOf("") }



    OutlinedTextField(
        value = gmail.value,
        onValueChange = { gmail.value = it },
        label = { Text(text = "gmail", fontSize = 11.sp) },
        placeholder = { Text(text = "gmail",fontSize = 12.sp) },
        textStyle = TextStyle(fontSize = 12.sp),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f)

            .height(55.dp)
            .padding(bottom = 0.dp)
            .defaultMinSize(minHeight = 0 .dp),

        )
    return gmail.value
}


@Composable
fun PassOutlinedTextField(){
    var username = remember { mutableStateOf("") }
    OutlinedTextField(
        value = username.value,
        onValueChange = { username.value = it },
        label = { Text(text = "username") },
        placeholder = { Text(text = "username") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f).height(15.dp),

        )

}


@Composable
fun OutlinedTextField(a:String,b: String):String{
    var phone = remember { mutableStateOf("") }



    OutlinedTextField(
        value = phone.value,
        onValueChange = { phone.value = it },
        label = { Text(text = a, fontSize = 11.sp) },
        placeholder = { Text(text =b,fontSize = 12.sp) },
        textStyle = TextStyle(fontSize = 12.sp),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f)

            .height(55.dp)
            .padding(bottom = 0.dp)
            .defaultMinSize(minHeight = 0 .dp),

        )
    return phone.value

}
@Composable
fun PhoneOutlinedTextField():String{
    var phone = remember { mutableStateOf("") }



    OutlinedTextField(
        value = phone.value,
        onValueChange = { phone.value = it },
        label = { Text(text = "Phone number", fontSize = 11.sp) },
        placeholder = { Text(text = "Phone number",fontSize = 12.sp) },
        textStyle = TextStyle(fontSize = 12.sp),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f)

            .height(55.dp)
            .padding(bottom = 0.dp)
            .defaultMinSize(minHeight = 0 .dp),

        )
    return phone.value

}

@Composable
fun AgeOutlinedTextField():String{


    var userage = remember { mutableStateOf("0") }



    OutlinedTextField(
        value = userage.value,
        onValueChange = { userage.value = it },
        label = { Text(text = "Age", fontSize = 11.sp) },
        placeholder = { Text(text = "age",fontSize = 12.sp) },
        textStyle = TextStyle(fontSize = 12.sp),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f)

            .height(55.dp)
            .padding(bottom = 0.dp)
            .defaultMinSize(minHeight = 0 .dp),

        )
    return userage.value
}

@Composable
fun GenderOutlinedTextField():String{


    var userage = remember { mutableStateOf("") }



    OutlinedTextField(
        value = userage.value,
        onValueChange = { userage.value = it },
        label = { Text(text = "Gender", fontSize = 11.sp) },
        placeholder = { Text(text = "Gender",fontSize = 12.sp) },
        textStyle = TextStyle(fontSize = 12.sp),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f)

            .height(55.dp)
            .padding(bottom = 0.dp)
            .defaultMinSize(minHeight = 0 .dp),

        )
    return userage.value
}
@Composable
fun adressOutlinedTextField():String{


    var userage = remember { mutableStateOf("") }



    OutlinedTextField(
        value = userage.value,
        onValueChange = { userage.value = it },
        label = { Text(text = "adress", fontSize = 11.sp) },
        placeholder = { Text(text = "adress",fontSize = 12.sp) },
        textStyle = TextStyle(fontSize = 12.sp),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f)

            .height(55.dp)
            .padding(bottom = 0.dp)
            .defaultMinSize(minHeight = 0 .dp),

        )
    return userage.value
}

@Composable
fun PaymentOutlinedTextField(){
    var username = remember { mutableStateOf("") }
    OutlinedTextField(
        value = username.value,
        onValueChange = { username.value = it },
        label = { Text(text = "username") },
        placeholder = { Text(text = "username") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f).height(15.dp),

        )

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FatherOfAppsTheme {
//        Profile_Greeting()
    }
}