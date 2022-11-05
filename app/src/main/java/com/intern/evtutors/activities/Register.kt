package com.intern.evtutors.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity


import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.composes.loading.CircularIndeterminateProgressBar
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
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SecondaryColor)
            //contentAlignment = Alignment.TopCenter
        )
        view()
    }
}
@Composable
fun view(registerViewModel: RegisterViewModel  = hiltViewModel()){
    val selectedValue = remember { mutableStateOf("") }
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val IDloading = remember { mutableStateOf(false) }

    val checkloading = remember { mutableStateOf(false) }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    val items = listOf("Student ", "Teacher")

    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }
    val scope = CoroutineScope(Dispatchers.IO + Job())

    val context = LocalContext.current
    if(IDloading.value){
        CircularIndeterminateProgressBar(isDisplayed = true, verticalBias = 0.3f)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(0.90f)
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
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
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
                            Icon(imageVector = if(confirmPasswordVisibility.value) Icons.Default.Lock else Icons.Default.Lock,
                                contentDescription ="Password Toggle" )
                        }
                    },
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = confirmPasswordValue.value,
                    onValueChange = { confirmPasswordValue.value = it
                                     },
                    label = { Text(text = "Confirm Password") },
                    placeholder = { Text(text = "Confirm Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),

                    trailingIcon = {
                        IconButton(onClick = {
                            confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                        }) {
                                Icon(imageVector = if(confirmPasswordVisibility.value) Icons.Default.Lock else Icons.Default.Lock,
                                    contentDescription ="Password Toggle" )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment

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
                                modifier = Modifier.padding(top = 10.dp)

                            )
                        }
                    }


                }
                Spacer(modifier = Modifier.padding(20.dp))
                Button(onClick = {
                    IDloading.value=true
                    val texterror=valiPassword( passwordValue.value)
                    val gmailerror=valiGmail(emailValue.value)
                    if(gmailerror!= null){
                        Toast.makeText(context, gmailerror, Toast.LENGTH_SHORT).show()
                    }else{
                        if(texterror != null){
                            Toast.makeText(context, texterror, Toast.LENGTH_SHORT).show()
                        }
                        else{
                            if( passwordValue.value != (confirmPasswordValue.value)){
                                Toast.makeText(context, "confirm PasswordValue error", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                if(selectedValue.value.length==7){
                                    scope.launch {
                                        val newUser =registerViewModel.registerTeacher(nameValue.value,
                                            passwordValue.value,
                                            emailValue.value)

                                        if(newUser != null){

                                            registerViewModel.generateCertificates(newUser!!.id)
                                            var intent: Intent = Intent(context, Login::class.java)
                                            context.startActivity(intent)
                                            IDloading.value =false
                                        }else{
                                            IDloading.value =false
                                            Handler(Looper.getMainLooper()).post {
                                                Toast.makeText(context, "Sign up again", Toast.LENGTH_SHORT).show()
                                            }

                                        }
                                    }

                                }else
                                    if(selectedValue.value.length==8){
                                        scope.launch {
                                            val newUser =registerViewModel.registerStudent(nameValue.value,
                                                passwordValue.value,
                                                emailValue.value)
                                            newUser?.let {
                                                var intent: Intent = Intent(context, Login::class.java)
                                                context.startActivity(intent)
                                                IDloading.value =false
                                            }
                                        }
                                    }else{
                                        IDloading.value =false
                                        Toast.makeText(context, "Sign up again", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
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
                if(nameValue.value.isNotEmpty()){
                    Spacer(modifier = Modifier.padding(150.dp))
                }else{
                    Spacer(modifier = Modifier.padding(20.dp))
                }



            }

        }

    }


}

fun spacer1() {
    @Composable
    fun spacer(i: Int){

    }
}




private fun valiPassword(pass: String): String? {
    if(pass.length < 8){
        return "Minimum 8 character"
    }
    if(!pass.matches(".*[A-Z].*".toRegex())){
        return "Must contain 1 upper-case character"
    }
    if(!pass.matches(".*[a-].*".toRegex())){
        return "Must contain 1 lower-case character"
    }
    if(!pass.matches(".*[@#$%^*&+=].*".toRegex())){
        return "Must contain 1 special character (@#\$%^*&+=)"
    }
    return null
}
private fun valiGmail(gmail: String):String?{
    if(gmail.equals("@gmail.com")){
        return "Gmail erro"
    }
    return null
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FatherOfAppsTheme {
        RegisterActivity()
    }
}