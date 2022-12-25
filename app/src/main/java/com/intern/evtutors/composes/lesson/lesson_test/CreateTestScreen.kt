package com.intern.evtutors.composes.lesson.lesson_test

import android.opengl.Visibility
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.composes.loading.CircularLoading
import com.intern.evtutors.composes.schedule.HeaderLine
import com.intern.evtutors.data.model_json.QuizJson
import com.intern.evtutors.data.models.Question
import com.intern.evtutors.view_models.LessonTestViewModel
import com.intern.evtutors.view_models.QuizAndTestViewModel
import com.miggue.mylogin01.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateTestScreen(
    quizId:Int?,
    lessonId: Int,
    quizTitle:String,
    backAction: ()->Unit,
    quizAndTestViewModel: QuizAndTestViewModel = hiltViewModel()
) {
    quizAndTestViewModel.stateQuizInput = quizTitle
    if(quizAndTestViewModel.stateSavingLoading) {
        CircularLoading(size = 40)
    } else {
        FatherOfAppsTheme {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(FourthColor)
            ) {
                stickyHeader {
                    CreateTestHeader(quizId, lessonId, backAction, quizAndTestViewModel)
                }
                item {
                    CreateTestBody(quizId, quizAndTestViewModel)
                }
            }

            if(quizAndTestViewModel.stateAddQuestion!="disable") {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ModalColor)
                        .clickable { quizAndTestViewModel.switchAddingQuestion("disable", null) }
                )
            }
            AnimatedVisibility(
                visible = quizAndTestViewModel.getOpeningQuestionBoxStatus(),
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
                        QuestionBox(
                            quizId,
                            quizAndTestViewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CreateTestHeader(
    quizId:Int?,
    lessonId: Int,
    backAction: () -> Unit,
    quizAndTestViewModel: QuizAndTestViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = ThirdColor),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 10.dp)
                .clickable { backAction() },
            text = "Cancel",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        if(quizId==null) {
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
                    .padding(end = 10.dp)
                    .clickable {
                        quizAndTestViewModel.addQuiz(lessonId = lessonId, backAction)
                    },
                text = "Save",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                text = "Edit test",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
                    .clickable {
                        quizAndTestViewModel.editQuiz(quizId, lessonId, backAction)
                    },
                text = "Save",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
    HeaderLine()
}

@Composable
fun CreateTestBody(
    quizId:Int?,
    quizAndTestViewModel: QuizAndTestViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TitleInput(
            title = "Title",
            placeHolder = "Enter title",
            quizAndTestViewModel
        ) {
            quizAndTestViewModel.stateQuizInput = it
        }
        Spacer(Modifier.height(5.dp))
        ListOfQuestions(quizId, quizAndTestViewModel)
    }
}

@Composable
fun TitleInput(
    title:String,
    placeHolder:String,
    quizAndTestViewModel: QuizAndTestViewModel,
    onChange: (String) -> Unit
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
            value = quizAndTestViewModel.stateQuizInput,
            onValueChange = onChange,
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
    quizId:Int?,
    quizAndTestViewModel: QuizAndTestViewModel
) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Questions (${quizAndTestViewModel.stateListQuestion?.size?:0})",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                modifier = Modifier
                    .padding(end = 10.dp),
                onClick = {quizAndTestViewModel.switchAddingQuestion("adding", null)},
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
        val listQuestion = quizAndTestViewModel.stateListQuestion
        if(quizId!=null) {
            if (listQuestion != null) {
                listQuestion.forEachIndexed { index, question ->
                    QuestionItem(index, question, quizAndTestViewModel)
                }
            } else {
                CircularLoading(size = 30)
                quizAndTestViewModel.getAllQuestion(quizId)
            }
        } else {
            if (listQuestion != null) {
                listQuestion.forEachIndexed { index, question ->
                    QuestionItem(index, question, quizAndTestViewModel)
                }
            } else {
                quizAndTestViewModel.stateListQuestion = mutableListOf<Question>()
            }
        }
    }
}

@Composable
fun QuestionItem(
    index:Int,
    question: Question,
    quizAndTestViewModel: QuizAndTestViewModel
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
                    .clickable { quizAndTestViewModel.switchAddingQuestion("disable", index) }
                    .background(GreenColor500)
            )
            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .weight(.6f)
                    .clickable {
                        quizAndTestViewModel.switchAddingQuestion("editing", index)
                    }
            ) {
                Text(
                    text = "Question ${index + 1}:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = question.question?:"",
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
