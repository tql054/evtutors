package com.intern.evtutors.ui.customer.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.activities.Login
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.models.Role
import com.intern.evtutors.data.models.User
import com.intern.evtutors.view_models.LoginViewModel
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
                    Profile_Greeting()
                }
            }
        }
    }
}

@Composable
fun Profile_Greeting(ProfileViewModel  : ProfileViewModel = hiltViewModel(),loginViewModel: LoginViewModel= hiltViewModel()) {
    var user = CustomerEntity(0, 0, "", 0, "", "", "", "", "", "", "", 0)
    var role: MutableSet<Role> = mutableSetOf()
//    used to RadioButton{
    val items = listOf("Male", "Female")
    val selectedValue = remember { mutableStateOf("") }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
//}
    val scope = CoroutineScope( Job()+ Dispatchers.Main)
    val context = LocalContext.current
    val age = remember { mutableStateOf("0") }

   //Status Edittext{
    val EdittextStatusName = remember { mutableStateOf(false) }
    val EdittextStatusadress = remember { mutableStateOf(false) }
    val EdittextStatusAge = remember { mutableStateOf(false) }
    val EdittextStatusGender = remember { mutableStateOf(false) }
    val StatusBottum = remember { mutableStateOf(true) }
    val EdittextStatusGmail = remember { mutableStateOf(false) }
    val EdittextStatusPhone = remember { mutableStateOf(false) }
    val EdittextStatuspasswprd = remember { mutableStateOf(false) }
    //}
    val update = remember { mutableStateOf(false) }
    var myuser = remember { mutableStateOf(user) }
    var userupdate: User = User(myuser.value.id,myuser.value.name,myuser.value.age,myuser.value.gender,myuser.value.address,myuser.value.phone,myuser.value.avatar,myuser.value.email,myuser.value.userName, password = null,role = role)
    var myuserUpdate = remember { mutableStateOf(userupdate) }

    myuserUpdate.value.password=""
    myuserUpdate.value.id=myuser.value.id
    myuserUpdate.value.name=myuser.value.name
    myuserUpdate.value.age=myuser.value.age
    myuserUpdate.value.gender=myuser.value.gender
    myuserUpdate.value.phone=myuser.value.phone
    myuserUpdate.value.address=myuser.value.address
    myuserUpdate.value.avatar=myuser.value.avatar
    myuserUpdate.value.email=myuser.value.email
    myuserUpdate.value.userName=myuser.value.userName


    var myuserCheck = remember { mutableStateOf(myuserUpdate.value) }

    ProfileViewModel.getuser()
    myuser.value= ProfileViewModel.myuser

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement =Arrangement.Top,
        horizontalAlignment =Alignment.CenterHorizontally,
    ){
        StatusBottum.value= header(name=myuser.value.name, gmail = myuser.value.email)
        if( StatusBottum.value){
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { EdittextStatusName.value=!EdittextStatusName.value }
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 25.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Person, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Name",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)
                        ){
                        if(EdittextStatusName.value){
                            var nameinput= NameOutlinedTextField()
                            if(nameinput.isNotEmpty()){
                                myuserUpdate.value.name =nameinput
                                update.value=true
                            }

                        }else{
                            Text(myuser.value.name)
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { EdittextStatusGmail.value=!EdittextStatusGmail.value }

            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 25.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Email, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Gmail",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusGmail.value){
                            var gmailinput = OutlinedTextField("email","email")
                            if(gmailinput.isNotEmpty()){
                                myuserUpdate.value.email =gmailinput
                                update.value=true
                            }
                        }else{
                            Text(myuser.value.email)
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {EdittextStatuspasswprd.value=!EdittextStatuspasswprd.value  }
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 25.dp),
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

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { EdittextStatusPhone.value=!EdittextStatusPhone.value }
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 25.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Phone, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Phone Number",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusPhone.value){
                           var phoneinput = OutlinedTextField("Phone","Phone")
                            if(phoneinput.isNotEmpty()){
                                myuserUpdate.value.phone =phoneinput
                                update.value=true
                            }
                        }else{
                            Text(myuser.value.phone)
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
                        .padding(start = 25.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Email, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Payment",fontSize = 18.sp)
                    }
//                    Row(modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 40.dp)){
//                        Text(text = "Payment",fontSize = 13.sp)
//                    }
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
                            .padding(start = 25.dp),
//                    verticalArrangement = Arrangement.Center
                        ){
                            Icon(Icons.Sharp.Favorite, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                            Text(text = "My certificates",fontSize = 18.sp)
                        }
//                        Row(modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(start = 40.dp)){
//                            Text(text = "Payment",fontSize = 13.sp)
//                        }
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
            }
        }else{
            Row(modifier = Modifier.fillMaxWidth()
                .clickable { EdittextStatusAge.value=!EdittextStatusAge.value }
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 25.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Person, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Age",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusAge.value){

                            age.value = OutlinedTextField("age","age")
                            if( age.value !=""){
                                update.value=true
                                age.value.let { myuserUpdate.value.age=it.toInt() }
                            }

                        }else{
                            Text(myuser.value.age.toString())
                        }
                    }
                }

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { EdittextStatusGender.value=!EdittextStatusGender.value }
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 25.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Check, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Gender",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusGender.value){
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {

                                items.forEach { item ->
                                    Row(
                                        //      verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.selectable(
                                            selected = isSelectedItem(item),
                                            onClick = { onChangeState(item) },
                                            role = androidx.compose.ui.semantics.Role.RadioButton
                                        )

                                    ) {
                                        RadioButton(
                                            selected = isSelectedItem(item),
                                            onClick = {
                                                selectedValue.value = item
                                            }

                                        )
                                        Text(
                                            text = item,
                                            modifier = Modifier.padding(top = 10.dp)

                                        )
                                    }
                                }


                            }
                            var gender =selectedValue.value
                            if(gender != null){
                                update.value=true
                                myuserUpdate.value.gender =selectedValue.value
                            }

                        }else{
                            Text(myuser.value.gender)
                        }
                    }
                }

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { EdittextStatusadress.value=!EdittextStatusadress.value }
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 25.dp),
//                    verticalArrangement = Arrangement.Center
                    ){
                        Icon(Icons.Sharp.Home, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                        Text(text = "Adress",fontSize = 18.sp)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)){
                        if(EdittextStatusadress.value){
                            var addressinput= OutlinedTextField("address","address")
                            if(addressinput!= null){
                                update.value =true
                                myuserUpdate.value.address=addressinput
                            }
                        }else{
                            Text(myuser.value.address)
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(100.dp))
        }
        Spacer(modifier = Modifier.height(45.dp))


        if(update.value){
            Button(modifier = Modifier.height(45.dp)
                .fillMaxWidth(0.7f)
                .background(Color.White),
                onClick = {
                    if(update.value){
                        scope.launch{
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

                    }
                    update.value =false

                },
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.buttonColors(backgroundColor = BlueText),
                border =  BorderStroke(1.dp, color = BlueText)
            ){
                Text(text = "UPDATE", color = Color.White )
            }
        }
        else{
            Button(modifier = Modifier.height(45.dp)
                .fillMaxWidth(0.7f)
                .background(Color.White),
                onClick = {


                },
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border =  BorderStroke(1.dp, color = BlueText)
            ){
                Text(text = "UPDATE", color = BlueText )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        TextButton(onClick = {
            var  customerEntity =CustomerEntity(1,0,"",0,"","","","","","","",0)
            loginViewModel.create(customerEntity)
            var intent: Intent = Intent(context, Login::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "LOG OUT",fontSize = 30.sp, color = BlueText)
        }
        Spacer(modifier = Modifier.height(45.dp))
    }
}

@Composable
fun header( name :String, gmail:String):Boolean{

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
                    .fillMaxWidth().background(Color.White),
                    horizontalArrangement =Arrangement.SpaceBetween
                ){
                    if( StatusBottumHD.value ){
                        Button(onClick = {},
                            modifier = Modifier
                                .height(45.dp)

                                .weight(0.50f)
                            ,
                            shape = RoundedCornerShape(10),
//
                        ){
                            Text(text = "Advanced", color = Color.White)
                        }
                    }else{
                        Button(onClick = {StatusBottumHD.value= !StatusBottumHD.value},
                            modifier = Modifier
                                .height(45.dp)
                                .weight(0.50f)

                            ,
                            shape = RoundedCornerShape(10,),
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
                                .weight(0.50f)
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
                                .weight(0.50f),
                            shape = RoundedCornerShape(15),
                            border =  BorderStroke(1.dp, color = BlueText),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ){
                            Text(text = "Basic", color = Color.Black)
                        }
                    }
                }
            }
        }
    }
    return StatusBottumHD.value

}
@Composable
fun Text(name: String){
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





@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FatherOfAppsTheme {
        Profile_Greeting()
    }
}