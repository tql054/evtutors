package com.intern.evtutors.composes.lesson

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intern.evtutors.R
import com.intern.evtutors.composes.home.CircleAvatar
import com.intern.evtutors.composes.schedule.HeaderLine
import com.miggue.mylogin01.ui.theme.*

@Composable
fun LessonDetailScreen(
    lessonId:Int
) {
    Surface() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LessonDetailHeader()
            HeaderLine()
            LessonTitle("Toeic 100")
            JoinCallButton()
            Spacer(Modifier.height(10.dp))
            TeachersLessonContent()
        }
    }
}

@Composable
fun LessonDetailHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = ThirdColor),
    ) {
        BackButton {}
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            text = "Welcome to",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp),
            onClick = { },
            contentPadding = PaddingValues(5.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = ItemColor_2),
        ) {
            Text(
                text = "View course",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(SecondaryColor)
                .align(Alignment.Center)
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(32.dp)
                .fillMaxHeight()
                .align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back Button",
                tint = Color.White
            )
        }
    }

}

@Composable
fun LessonTitle(title:String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = title,
        fontSize = 40.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun JoinCallButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(10.dp, 0.dp)
            .clip(CircleShape)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = "Join a call now!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play icon",
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeachersLessonContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 400.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(FourthColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LessonContentInfo()
            LazyVerticalGrid(
                modifier = Modifier.padding(10.dp),
                cells = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item() {
                    LessonFeatureItem(
                        title = "Quiz          and test",
                        color1 = ItemColor_2,
                        color2 = Red500,
                        color3 = Red700,
                        R.drawable.icon_quiz
                    ) {
                    }
                }
                item() {
                    LessonFeatureItem(
                        title = "Docs",
                        color1 = Brown300,
                        color2 = Brown500,
                        color3 = Brown700,
                        R.drawable.icon_document
                    ) {
                    }
                }
                item() {
                    LessonFeatureItem(
                        title = "Slides",
                        color1 = ThirdColor,
                        color2 = SecondaryColor,
                        color3 = PrimaryColor,
                        R.drawable.icon_slide
                    ) {
                    }
                }
                item() {
                    LessonFeatureItem(
                        title = "Rate now",
                        color1 = GreenColor300,
                        color2 = GreenColor500,
                        color3 = GreenColor700,
                        R.drawable.icon_star
                    ) {
                    }
                }
            }
        }
    }
}

@Composable
fun LessonContentInfo(
    //idUser,
) {
    //check user's role
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(10.dp, 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Fake avatar
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleAvatar()
                //
                Column(
                    modifier = Modifier.padding(start = 5.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "Student",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = "Le Van A",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier.padding(5.dp),
                border = BorderStroke(1.dp, PrimaryColor),
                shape = RoundedCornerShape(50), // = 50% percent
            ){
                Text(
                    text = "Chat now",
                    fontSize = 8.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun LessonFeatureItem(
    title:String,
    color1:Color,
    color2:Color,
    color3:Color,
    icon:Int,
    onClick: ()->Unit
) {
    Box(
        modifier = Modifier
            .size(150.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            color1,
                            color2,
                            color3,
                        )
                    )
                )
                .padding(10.dp, 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Feature icon",
                contentScale = ContentScale.Inside
            )
            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun LessonDetailPreview() {
    LessonDetailScreen(2)
}
