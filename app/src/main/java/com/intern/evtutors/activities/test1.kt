package com.intern.evtutors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.intern.evtutors.R
import com.intern.evtutors.data.database.entities.CustomerEntity


import com.intern.evtutors.ui.customer.login.LoginViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

public  var loadView = false
@AndroidEntryPoint
class test1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
//            FatherOfAppsTheme {
                // A surface container using the 'background' color from the theme
                SigInScreen()
//            }
        }
    }
}
@Composable
 fun showMessage(message:String){
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SigInScreen(loginViewModel  : LoginViewModel = hiltViewModel()) {
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var offset by remember { mutableStateOf(0) }
    val (focusUsername,focusPassword) = remember { FocusRequester.createRefs()}
    val keyboardController =  LocalSoftwareKeyboardController.current
    var isPasswordVisible by remember{ mutableStateOf(false) }

    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable {offset = 0  }
                .offset { IntOffset(offset, offset) }
                .padding(horizontal = 40.dp)) {
                Text(text = "Login", style = MaterialTheme.typography.h1)

                OutlinedTextField(value = username, onValueChange = {username = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusUsername),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {focusPassword.requestFocus()}),
                    singleLine = true,
                    label = {Text(text = "Username")}
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusPassword),
                    value = password,
                    onValueChange ={password = it},
                    label = { Text(text = "Password")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions (onDone = {keyboardController?.hide()}),
                    visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {isPasswordVisible = !isPasswordVisible}) {
                            Icon(imageVector = if(isPasswordVisible) Icons.Default.Lock else Icons.Default.Lock,
                                contentDescription ="Password Toggle" )

                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange = {})
                        Text(text = "Remember me",fontSize = 12.sp)
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Forgot Password",fontSize = 12.sp)
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                login(loginViewModel,username,password)

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), Arrangement.Center
                ){
                    Text(text = "Or log in with", fontSize =14.sp)
                }
                Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceAround){
                    Button(onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(45.dp)
                            .width(45.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_fb), contentDescription = "Facebook logo",
                            modifier = Modifier
                                .weight(1f)
                                .size(40.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                        )
                    }
                    Button(onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(45.dp)
                            .width(45.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "Google logo",
                            modifier = Modifier
                                .weight(1f)
                                .size(40.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                        )
                    }
                    Button(onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(45.dp)
                            .width(45.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_twitter), contentDescription = "Twitter logo",
                            modifier = Modifier
                                .weight(1f)
                                .size(40.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center,verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Don't have account?",fontSize = 14.sp)
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Register")
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
            }

//            Box(modifier = Modifier
//                .fillMaxWidth()
//
//                .fillMaxHeight(fraction = 0.30f),
//                Alignment.TopEnd,
//            ){
//                Image(painter = painterResource(id = R.drawable.onboar1), contentDescription = "",
//                    modifier = Modifier.fillMaxSize(),contentScale = ContentScale.FillBounds
//                )
//
//
//            }
        }


    }

}
@Composable
fun login(loginViewModel:LoginViewModel,
          username:String,
          password:String ){
    var log= false
    val users by loginViewModel.listPots.observeAsState()

    val context = LocalContext.current
    Button(onClick = {
        loginViewModel.create(CustomerEntity(1,3,"Le Tuan", 21))
        var intent: Intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "Log in")
    }

}



//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FatherOfAppsTheme {
//        SigInScreen()
//    }
//}