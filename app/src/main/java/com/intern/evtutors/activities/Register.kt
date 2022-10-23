package com.intern.evtutors.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity


import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.ui.customer.login.LoginViewModel
import com.intern.evtutors.ui.customer.register.RegisterViewModel

import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import com.miggue.mylogin01.ui.theme.SecondaryColor
import com.miggue.mylogin01.ui.theme.whiteBackground
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatherOfAppsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RegisterActivity()
                }
            }
        }
    }
}

@Composable
fun RegisterActivity() {



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SecondaryColor),
            contentAlignment = Alignment.TopCenter

        ) {
//            Image(image)
        }

        view()

    }
}
@Composable
fun view(registerViewModel: RegisterViewModel  = hiltViewModel()){
    val selectedValue = remember { mutableStateOf("") }
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    val items = listOf("Student ", "Teacher")

    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }
    val scope = CoroutineScope(Dispatchers.IO + Job())
    val user by registerViewModel.listPots.observeAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .clip(shape = RoundedCornerShape(topStart=30.dp, topEnd=30.dp))
            .background(whiteBackground)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",style = MaterialTheme.typography.h1,

                )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally
                ) {
                OutlinedTextField(
                    value = nameValue.value,
                    onValueChange = { nameValue.value = it },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                OutlinedTextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

//                    OutlinedTextField(
//                        value = phoneValue.value,
//                        onValueChange = { phoneValue.value = it },
//                        label = { Text(text = "Phone Number") },
//                        placeholder = { Text(text = "Phone Number") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(0.8f),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
//                    )

                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {

                        }
                    },
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                OutlinedTextField(
                    value = confirmPasswordValue.value,
                    onValueChange = { confirmPasswordValue.value = it },
                    label = { Text(text = "Confirm Password") },
                    placeholder = { Text(text = "Confirm Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),

                    trailingIcon = {
                        IconButton(onClick = {
                            confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                        }) {
//                                Icon(imageVector = if(confirmPasswordVisibility.value) Icons.Default.Lock else Icons.Default.Lock,
//                                    contentDescription ="Password Toggle" )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly

                ){

                    items.forEach { item ->
                        Row(
                            //      verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.selectable(
                                selected = isSelectedItem(item),
                                onClick = { onChangeState(item) },
                                role = Role.RadioButton
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
                                // modifier = Modifier.fillMaxWidth(0.40f)
                            )
                        }
                    }


                }
                Spacer(modifier = Modifier.padding(20.dp))
                Button(onClick = {

                    if(selectedValue.value.length==7){
                        scope.launch {
                                registerViewModel.fetchRegisterTeacher(nameValue.value,
                                                                    passwordValue.value,
                                                                    emailValue.value)

                                if(user != null){
                                    var intent: Intent = Intent(context, Login::class.java)
                                    context.startActivity(intent)
                                }
                        }

                    }else
                        if(selectedValue.value.length==8){
                            scope.launch {
                                registerViewModel.fetchRegisterStudent(nameValue.toString(),
                                                                        passwordValue.toString(),
                                                                        emailValue.toString())
                            }

                        }else{

                        }

                },

                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)) {
                    Text(text="Sign Up",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "Login Instead",
                    modifier = Modifier.clickable(onClick = {
//                            navController.navigate("login_page"){
//                                popUpTo = navController.graph.startDestination
//                                launchSingleTop = true
//                            }
                    })
                )
                Spacer(modifier = Modifier.padding(20.dp))

            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FatherOfAppsTheme {
        RegisterActivity()
    }
}