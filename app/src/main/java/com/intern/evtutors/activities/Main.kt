package com.intern.evtutors.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.intern.evtutors.activities.ui.Screen
import com.intern.evtutors.activities.ui.theme.FatherOfAppsTheme
import com.intern.evtutors.composes.home.HomeBaseScreen

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatherOfAppsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}
@Composable
fun test(){
    Text(text = "sdfsdfsd")
}
@Composable
fun Greeting2(name: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Screen.Home.route") {
        composable("Screen.Home.route") {
            test1()
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview3() {
//    FatherOfAppsTheme {
//        Greeting2("Android")
//    }
//}

