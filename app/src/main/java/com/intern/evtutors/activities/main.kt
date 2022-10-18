package com.intern.evtutors.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme

class main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            FatherOfAppsTheme {
                // A surface container using the 'background' color from the theme
                Greeting()
            }

        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_page") {
        composable("login_page") {
            showScreen()
        }
       // composable("register_page", content = { RegisterPage(navController = navController) })
    }
}

@Composable
fun showScreen(){
//    SigInScreen()
    Text(text = "sdfsdfsdf")
}
