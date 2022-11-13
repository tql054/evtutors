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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.intern.evtutors.activities.Login
import com.intern.evtutors.data.database.entities.CustomerEntity
import com.intern.evtutors.data.models.Role
import com.intern.evtutors.data.models.User
import com.intern.evtutors.data.models.getjwtToken
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
//                    Profile_Greeting()
                }
            }
        }
    }
}

@Composable
fun Profile_Greeting(navHostController: NavHostController, ProfileViewModel  : ProfileViewModel = hiltViewModel(), loginViewModel: LoginViewModel= hiltViewModel()) {

//    used to RadioButton{
    val items = listOf("Male", "Female")
    val selectedValue = remember { mutableStateOf("") }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
//}
    val scope = CoroutineScope( Job()+ Dispatchers.Main)
    val context = LocalContext.current
    val age = remember { mutableStateOf("0") }

   // Edittext{
    val EdittextName = remember { mutableStateOf("") }
    val EdittextGender = remember { mutableStateOf("") }
    val EdittextStatus = remember { mutableStateOf(false) }
    val Edittextadress = remember { mutableStateOf("") }
    val EdittextAge = remember { mutableStateOf("0") }
    val EdittextStatusGender = remember { mutableStateOf(false) }
    val EdittextGmail = remember { mutableStateOf("") }
    val EdittextPhone = remember { mutableStateOf("") }
    //}
    var user = CustomerEntity(0, 0, "", 0, "", "", "", "", "", "", "", 0)
    var role: MutableSet<Role> = mutableSetOf()
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
    ProfileViewModel.getuser()
    myuser.value= ProfileViewModel.myuser


    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement =Arrangement.Top,
        horizontalAlignment =Alignment.CenterHorizontally,
    ){
        header(name=myuser.value.name, gmail = myuser.value.email)
        Row(Modifier.fillMaxWidth(0.9f).padding(end = 10.dp),
            Arrangement.End) {
            if(update.value){
                Text(text = "Edit",fontStyle= FontStyle.Italic, color = Color(0xFF1655F5), modifier = Modifier.clickable { EdittextStatus.value=!EdittextStatus.value } )
            }else{
                Text(text = "Edit",fontStyle= FontStyle.Italic, modifier = Modifier.clickable { EdittextStatus.value=!EdittextStatus.value } )
            }

        }
        EdittextName.value=itemInfo(Icons.Sharp.Person,"Name",myuser.value.name,EdittextStatus.value)
        Spacer(modifier = Modifier.height(10.dp))
        EdittextGmail.value=itemInfo(Icons.Sharp.Email,"Gmail",myuser.value.email,EdittextStatus.value)
        Spacer(modifier = Modifier.height(10.dp))
        EdittextPhone.value =itemInfo(Icons.Sharp.Phone,"Phone",myuser.value.phone,EdittextStatus.value)
        Spacer(modifier = Modifier.height(10.dp))
        EdittextAge.value = itemInfo(Icons.Sharp.Person,"Age    ",myuser.value.age.toString(),EdittextStatus.value)
        Spacer(modifier = Modifier.height(10.dp))
        Edittextadress.value= itemInfo(Icons.Sharp.Home,"Phone",myuser.value.address,EdittextStatus.value)
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth(0.85f)
            .clickable { EdittextStatusGender.value=!EdittextStatusGender.value },
            Arrangement.Start,
            Alignment.CenterVertically,

        ){
            Icon(Icons.Sharp.Check, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
            Text(text = "Gender",fontSize = 18.sp)
            if(EdittextStatus.value){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    items.forEach { item ->
                        Row(
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
                if(selectedValue.value =="Male"){
                    EdittextGender.value="M"
                }else{
                    EdittextGender.value="F"
                }

            }else{
                Text(myuser.value.gender)
            }
        }

        if(EdittextName.value!="" &&
            EdittextGmail.value!="" &&
            EdittextPhone.value !="" &&
            EdittextAge.value!="" &&
            Edittextadress.value!=""&&
            EdittextGender.value!=""
        )
        {
            update.value =true
            myuserUpdate.value.name=EdittextName.value
            myuserUpdate.value.email= EdittextGmail.value
            myuserUpdate.value.phone= EdittextPhone.value
            myuserUpdate.value.age=EdittextAge.value.toInt()
            myuserUpdate.value.gender=EdittextGender.value
            myuserUpdate.value.address=Edittextadress.value

        }
        Spacer(modifier = Modifier.height(10.dp))
        if(myuser.value.roleID==3){
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(0.85f)
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth()
                    ){
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp),
                        ){
                            Icon(Icons.Sharp.Favorite, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
                            Text(text = "My certificates",fontSize = 18.sp)
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
        Spacer(modifier = Modifier.height(45.dp))
        if(update.value){
            Button(modifier = Modifier.height(45.dp)
                .fillMaxWidth(0.7f)
                .background(Color.White),
                onClick = {
                    if(update.value){
                        scope.launch{
                            loginViewModel.UpdateAccount(myuser.value.id,myuserUpdate.value)

                            if(loginViewModel.myuserupdate.id != 0){
                                Handler(Looper.getMainLooper()).post {
                                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                }
                                var usertoken =getjwtToken(myuserUpdate.value,"")
                                var userUpdateLocal= loginViewModel.cover(usertoken)
                                loginViewModel.create(userUpdateLocal!!)
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
fun header( name :String, gmail:String){

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
            }
        }
    }

}
@Composable
fun Text(name: String){
    Text(text = name,fontSize = 13.sp)
}
@Composable
fun OutlinedTextFieldinput(a:String,b: String):String{
    var input = remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth()
            .requiredHeight(50.dp)
            .height(50.dp)
            .border(width = 2.dp,
                brush = Brush.horizontalGradient(listOf(Blue, Green)),
                shape = RoundedCornerShape(25.dp))
            .defaultMinSize(minHeight = 0 .dp),
        value = input.value,
        onValueChange = { input.value = it },
        placeholder = { Text(text =b,fontSize = 15.sp, color =Color(0xFF20AFFF) ) },
        textStyle = TextStyle(fontSize = 15.sp, color =Black),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = whitebacground,
            unfocusedBorderColor = whitebacground,
            focusedLabelColor = Black,
            cursorColor = Color(0xFFCFDCFF)
        ),

        )
    return input.value

}
@Composable
fun itemInfo(imageVector: ImageVector,title:String, name: String, EdittextStatus:Boolean):String{
    var input = remember { mutableStateOf("") }
    Row(modifier = Modifier
        .fillMaxWidth(0.85f)
        .width(75.dp),
        Arrangement.Start,
        Alignment.CenterVertically,
    ){
        Icon(imageVector, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
        Text(text = "$title: ",fontSize = 18.sp)
        Spacer(Modifier.width(10.dp))

        if(EdittextStatus){
             input.value= OutlinedTextFieldinput(name,name)
        }else{
            Text(name)
        }

    }
    return input.value
}



