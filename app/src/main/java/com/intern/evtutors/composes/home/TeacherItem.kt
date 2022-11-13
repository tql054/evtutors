package com.intern.evtutors.composes.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intern.evtutors.data.models.Teacher
import com.intern.evtutors.di.FatherOfApps
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import com.miggue.mylogin01.ui.theme.ItemColor_1
import com.miggue.mylogin01.ui.theme.PrimaryColor
import com.miggue.mylogin01.ui.theme.RedColor

@Composable
fun TeacherItem(
    tutor: Teacher

) {
    val roundedShape = RoundedCornerShape(20.dp)
    FatherOfAppsTheme {
        Surface(
            modifier = Modifier
                .width(130.dp)
                .height(140.dp)
                .clip(shape = roundedShape)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = ItemColor_1)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                        .padding(top = 5.dp, end =8.dp, bottom = 5.dp, start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleAvatar()
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = tutor.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(95.dp)
                        .clip(shape = roundedShape)
                        .align(Alignment.BottomStart)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            InfoButton(imageVector = Icons.Default.Star, title = "4.5")
                            Text(
                                text = "Expertise: B1 - B2",
                                fontSize = 8.sp,
                                color = RedColor
                            )
                        }
                        TestList()
                    }
                }
            }
        }
    }
}

@Composable
fun TestList(
//    testList:List<String>
) {
    val list = listOf(
        "9 Ielts (Date exam 06/10/2022)",
        "Graduated Da Nang Foreign Language University.",
        "Graduated Da Nang Foreign Language University."
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(count = list.size) {
            index ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Canvas(modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(6.dp)){
                    drawCircle(Color.Black)
                }
                Text(text = list[index],fontSize = 6.sp)
            }
        }
    }
}

//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FatherOfAppsTheme {
//        TeacherItem()
//    }
//}
