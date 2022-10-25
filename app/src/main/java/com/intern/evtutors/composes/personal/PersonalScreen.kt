package com.intern.evtutors.composes.personal

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.ui.customer.login.LoginViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme

@Composable
fun PersonalScreen(
    TypeOfUser:Int,
    viewModel: LoginViewModel = hiltViewModel()
) {
    viewModel.getuser()
    when(TypeOfUser) {
        2 -> {
            Surface() {
                Column() {
                    var user = viewModel.user
                    Text(text = "Count: ${user.name}")
                }
            }
        }

        3 -> {
            TutorInfoPage()
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorInfoPage(
) {
    FatherOfAppsTheme {
        LazyColumn(
            modifier = Modifier.padding(
                top = 5.dp,
                start = 10.dp,
                end = 10.dp
            )
        ) {
            stickyHeader {
                TutorInfoHeader()
            }
        }
    }
}

@Composable
fun TutorInfoHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier.width(100.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .background(Color.Blue)
                        .align(Alignment.CenterStart),
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Certificate Icon",
                    tint = Color.White
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = "Merit of:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }


        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    FatherOfAppsTheme {
//        TutorInfoPage()
    }
}

