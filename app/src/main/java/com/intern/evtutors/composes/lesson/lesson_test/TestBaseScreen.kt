package com.intern.evtutors.composes.lesson.lesson_test

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intern.evtutors.composes.lesson.BackButton
import com.intern.evtutors.composes.schedule.HeaderLine
import com.miggue.mylogin01.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestBaseScreen(
    openAddTest: () -> Unit,
    backAction: () -> Unit
) {
    FatherOfAppsTheme {
        Scaffold(
            content = {
                LazyColumn() {
                    stickyHeader {
                        TestHeader(openAddTest, backAction)
                        HeaderLine()
                    }
                    item {
                        LazyVerticalGrid(
                            modifier = Modifier
                                .padding(10.dp)
                                .height(400.dp),
                            cells = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            item {
                                TestItem(testName = "Test of middle semester")
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun TestHeader(
    openAddTest: () -> Unit,
    backAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(ThirdColor),
    ) {
        BackButton {backAction()}
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            text = "Quiz and test",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp),
            onClick = { openAddTest() },
            contentPadding = PaddingValues(8.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Red500),
        ) {
            Text(
                text = "Add test",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun TestItem(
    testName:String
) {
//     Box() {
         Column(
             modifier = Modifier
                 .size(160.dp, 120.dp)
                 .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                 .background(SecondaryColor)
         ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.25f)
                    .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                    .background(GreenColor500)
            )
             Text(
                 modifier = Modifier
                     .fillMaxWidth()
                     .weight(.6f)
                     .padding(10.dp, 2.dp),
                 text = testName,
                 fontSize = 12.sp,
                 fontWeight = FontWeight.Bold,
                 color = Color.White
             )

             Row(
                 modifier = Modifier
                     .fillMaxWidth()
                     .weight(.2f),
                 verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.SpaceAround
             ) {
                 for(i in 1..6) {
                     Box(
                         modifier = Modifier
                             .size(7.dp)
                             .clip(CircleShape)
                             .background(PrimaryColor)
                     )
                 }
             }
         }
//     }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun LessonTestPreview() {
//    TestBaseScreen()
}