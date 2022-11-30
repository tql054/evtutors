package com.intern.evtutors.composes.lesson.lesson_test

import android.opengl.Visibility
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.composes.schedule.HeaderLine
import com.intern.evtutors.view_models.LessonTestViewModel
import com.miggue.mylogin01.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateTestScreen(
    lessonTestViewModel: LessonTestViewModel = hiltViewModel()
) {
    FatherOfAppsTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(FourthColor)
        ) {
            stickyHeader {
                CreateTestHeader()
            }
            item {
                CreateTestBody(lessonTestViewModel)
            }
        }

        if(lessonTestViewModel.stateAddQuestion) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ModalColor)
                    .clickable { lessonTestViewModel.stateAddQuestion = false }
            )
        }
        AnimatedVisibility(
            visible = lessonTestViewModel.stateAddQuestion,
            enter = slideInVertically(
                initialOffsetY = { 1000 },
                animationSpec = tween(
                    easing = LinearEasing // interpolator
                )
            )+ fadeIn(),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 57.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.08f)
                        .background(NoColor)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.92f)
                ) {
                    QuestionBox(index = 1)
                }
            }
        }
    }
}

@Composable
fun CreateTestHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = ThirdColor),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 10.dp),
            text = "Cancel",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = "Create test",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp),
            text = "Save",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
    HeaderLine()
}

@Composable
fun CreateTestBody(
    lessonTestViewModel: LessonTestViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TitleInput(
            title = "Title",
            placeHolder = "Enter title",
        )
        Spacer(Modifier.height(5.dp))
        ListOfQuestions(lessonTestViewModel)
    }
}

@Composable
fun TitleInput(
    title:String,
    placeHolder:String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent {
//                        focusState ->
//                    when {
//                        focusState.hasFocus -> {
//                            stateValidate = value != ""
//                        }
//                    }
                }
                .background(Color.White)
            ,
            value = "",
            onValueChange = {  },
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.Gray,
                disabledTextColor = Color.Black
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(text = placeHolder)
            }
        )
//        if(!stateValidate) {
//            Text(
//                text = "This one is not allowed empty",
//                fontSize = 10.sp,
//                fontWeight = FontWeight.Light,
//                fontStyle = FontStyle.Italic,
//                color = Red500
//            )
//        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ListOfQuestions(
//    view model to get number of question
    lessonTestViewModel: LessonTestViewModel
) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Questions (2)",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                modifier = Modifier
                    .padding(end = 10.dp),
                onClick = {lessonTestViewModel.stateAddQuestion = true},
                contentPadding = PaddingValues(15.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Red500),
            ) {
                Text(
                    modifier = Modifier.padding(5.dp, 0.dp),
                    text = "Add",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        QuestionItem(index = 1, question = "There is question")
        QuestionItem(index = 2, question = "There is question")
    }
}

@Composable
fun QuestionItem(
    index:Int,
    question:String
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)
        .clip(RoundedCornerShape(15.dp))
        .height(70.dp))
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(.03f)
//                .clip(RoundedCornerShape(topStart = 40.dp, bottomStart = 40.dp))
                    .background(GreenColor500)
            )

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .weight(.6f)
            ) {
                Text(
                    text = "Question $index:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = "Question fhdasi fhuasdifh",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Gray300
                )
            }

            Row(
                modifier = Modifier
                    .weight(0.05f)
                    .align(Alignment.Top)
                    .padding(top = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                for(i in 1..3) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(GreenColor500)
                    )
                }
            }
        }
    }
}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun CreateTestPreview() {
//    CreateTestScreen()
}
