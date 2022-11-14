package com.intern.evtutors.ui.customer.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
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
import androidx.compose.ui.graphics.RectangleShape
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
import coil.compose.rememberAsyncImagePainter
import com.intern.evtutors.R
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
    ProfileViewModel.getuser()
//    used to RadioButton{
    val items = listOf("Male", "Female")
    val selectedValue = remember { mutableStateOf("") }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
//}
    val scope = CoroutineScope( Job()+ Dispatchers.Main)
    val context = LocalContext.current
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

    val update = remember { mutableStateOf(false) }
    var myuser = remember { mutableStateOf(user) }
    myuser.value= ProfileViewModel.myuser
    var role: MutableSet<Role> = mutableSetOf(Role(myuser.value.roleID,""))
    var userupdate = User(myuser.value.id,myuser.value.name,myuser.value.age,myuser.value.gender,myuser.value.address,myuser.value.phone,myuser.value.avatar,myuser.value.email,myuser.value.userName, password = null,role = role)
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
    myuserUpdate.value.role= mutableSetOf(Role(myuser.value.roleID,""))




    myuserUpdate.value.name=EdittextName.value
    myuserUpdate.value.email= EdittextGmail.value
    myuserUpdate.value.phone= EdittextPhone.value
    if( EdittextAge.value.matches("[0-9]{1,3}$".toRegex()))
        {
        myuserUpdate.value.age=EdittextAge.value.toInt()
    }

    myuserUpdate.value.gender=if(EdittextGender.value=="Female"){"F"}else{"M"}
    myuserUpdate.value.address=Edittextadress.value


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
                Text(text = "Edit",fontStyle= FontStyle.Italic, modifier = Modifier.clickable {
                    update.value =!update.value
                    EdittextStatus.value=!EdittextStatus.value } )
            }

        }
        EdittextName.value=itemInfo(Icons.Sharp.Person,"Name",myuser.value.name,EdittextStatus.value,myuser.value.name)
        Spacer(modifier = Modifier.height(10.dp))
        EdittextGmail.value=itemInfo(Icons.Sharp.Email,"Gmail",myuser.value.email,EdittextStatus.value,myuser.value.email)
        Spacer(modifier = Modifier.height(10.dp))
        EdittextPhone.value =itemInfo(Icons.Sharp.Phone,"Phone",myuser.value.phone,EdittextStatus.value,myuser.value.phone)
        Spacer(modifier = Modifier.height(10.dp))
        EdittextAge.value = itemInfo(Icons.Sharp.Person,"Age     ",myuser.value.age.toString(),EdittextStatus.value,myuser.value.age.toString())
        Spacer(modifier = Modifier.height(10.dp))
        Edittextadress.value= itemInfo(Icons.Sharp.Home,"Adress",myuser.value.address,EdittextStatus.value,myuser.value.address)
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth(0.85f)
            .clickable { EdittextStatusGender.value=!EdittextStatusGender.value },
            Arrangement.Start,
            Alignment.CenterVertically,

        ){
            Icon(Icons.Sharp.Check, contentDescription = null, Modifier.size(35.dp).padding(end =10.dp),Purple700)
            Text(text = "Gender:  ",fontSize = 18.sp)
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
                }
                if(selectedValue.value==""){
                    EdittextGender.value="Female"
                }
            }else{
                if(myuser.value.gender =="M"){
                    Text("Male")
                }else{
                    Text("Female")
                }

            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        if(myuser.value.roleID==3){
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(0.85f)
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ){
                        Row(modifier = Modifier
                            .fillMaxWidth()

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
                            .fillMaxWidth(),
                            Arrangement.Top,
                            Alignment.End
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
        Spacer(modifier = Modifier.height(25.dp))
        if(update.value){
            Button(modifier = Modifier.height(40.dp)
                .fillMaxWidth(0.7f)
                .background(Color.White),
                onClick = {
                    var checkValidate= validate(myuserUpdate.value.phone,myuserUpdate.value.email,EdittextAge.value)
                    if(checkValidate==2){
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(context, "phone number format error", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        if(checkValidate==1){
                            Handler(Looper.getMainLooper()).post {
                                Toast.makeText(context, "gmail format error", Toast.LENGTH_SHORT).show() }
                        }
                        else{
                            if(checkValidate==3){
                                Handler(Looper.getMainLooper()).post {
                                    Toast.makeText(context, "Age format error", Toast.LENGTH_SHORT).show() }
                            }
                        }
                    }
                    if(update.value && checkValidate==0){
                        scope.launch{

                            loginViewModel.UpdateAccount(myuser.value.id,myuserUpdate.value)

                            if(loginViewModel.myuserupdate != null){

                                EdittextStatus.value =false
                                update.value=false
                                Handler(Looper.getMainLooper()).post {
                                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                }

                                var usertoken =getjwtToken(myuserUpdate.value,"")
                                var userUpdateLocal= loginViewModel.cover(usertoken)
                                loginViewModel.create(userUpdateLocal!!)

                            }
                            else{
                                Handler(Looper.getMainLooper()).post {
                                    Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }


                    }


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
fun validate(numberPhone:String,gmail: String, age:String):Int{
        if(!Patterns.EMAIL_ADDRESS.matcher(gmail).matches() ||
            !gmail.endsWith("@gmail.com")
                ){
           return 1
        }
        if(numberPhone.length!=10 || !numberPhone.matches("[0-9]{10}$".toRegex())){
            return 2
        }
        if( !age.matches("[0-9]{1,3}$".toRegex()))
        {
            return 3
        }

    return 0
}

@Composable
fun header( name :String, gmail:String){



    Column(modifier = Modifier
        .fillMaxWidth()
        .background(SecondaryColor)
        .fillMaxHeight(0.40f),
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(15.dp)
            ,
            Alignment.Center,
        ){
            Column(
                horizontalAlignment =Alignment.CenterHorizontally,
            ) {

                Image(painter = rememberAsyncImagePainter("https://tse2.mm.bing.net/th?id=OIP.XgK18C8qMMhf9KZwMWX-twHaE7&pid=Api&P=0")
                    , contentDescription = "",
                    modifier = Modifier.size(120.dp).clip(CircleShape),contentScale = ContentScale.FillBounds
                )
                Text(text = name, fontSize = 25.sp, color = Color.Black)
                Text(text =gmail, fontSize = 15.sp, color = Color.Black)


            }
        }
    }

}
@Composable
fun Text(name: String){
    Text(text = name,fontSize = 15.sp)
}
@Composable
fun OutlinedTextFieldinput(a:String,b: String):String{
    var input = remember { mutableStateOf(a) }
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
fun itemInfo(imageVector: ImageVector,title:String, name: String, EdittextStatus:Boolean,inputDefault:String):String{
    var input = remember { mutableStateOf(name) }
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
             input.value= OutlinedTextFieldinput(inputDefault,name)
        }else{

            Text(name)
        }

    }
    return input.value
}



