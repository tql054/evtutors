package com.intern.evtutors.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity


import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    val items = listOf("Student ", "Teacher")

    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }
    val scope = CoroutineScope(Dispatchers.IO + Job())

    val context = LocalContext.current

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
                    val texterro=valiPassword( passwordValue.value)
                    val gmailerro=valiGmail(emailValue.value)
                    if(gmailerro!= null){
                        Toast.makeText(context, gmailerro, Toast.LENGTH_SHORT).show()
                    }else{
                        if(texterro != null){
                            Toast.makeText(context, texterro, Toast.LENGTH_SHORT).show()
                        }
                        else{
                            if( passwordValue.value != (confirmPasswordValue.value)){
                                Toast.makeText(context, "confirm PasswordValue erro", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                if(selectedValue.value.length==7){
                                    scope.launch {
                                        val userT =registerViewModel.fetchRegisterTeacher(nameValue.value,
                                            passwordValue.value,
                                            emailValue.value)

                                        if(userT != null){
                                            var intent: Intent = Intent(context, Login::class.java)
                                            context.startActivity(intent)
                                        }else{
                                            Toast.makeText(context, "Sign up again", Toast.LENGTH_SHORT).show()
                                        }
                                    }

                                }else
                                    if(selectedValue.value.length==8){
                                        scope.launch {
                                            val userT =registerViewModel.fetchRegisterStudent(nameValue.value,
                                                passwordValue.value,
                                                emailValue.value)

                                            if(userT != null){
                                                var intent: Intent = Intent(context, Login::class.java)
                                                context.startActivity(intent)
                                            }
                                        }

                                    }else{
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
                spacer(1)


            }

        }

    }


}

fun spacer1() {
    @Composable
    fun spacer(i: Int){

    }
}

@Composable
fun spacer(i: Int){
    Spacer(modifier = Modifier.padding(20.dp))
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