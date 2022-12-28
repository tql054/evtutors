package com.intern.evtutors.composes.lesson.lesson_test

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intern.evtutors.data.models.Question
import com.intern.evtutors.view_models.QuizAndTestViewModel
import com.miggue.mylogin01.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuestionBox(
    quizId: Int?,
    quizAndTestViewModel: QuizAndTestViewModel
) {
    val focusManager = LocalFocusManager.current
    focusManager.clearFocus()
    if(quizAndTestViewModel.stateListAnswer==null) {
        if(quizAndTestViewModel.stateIndexCurrentQuestion!=null) {
            quizAndTestViewModel.stateQuestionInput = quizAndTestViewModel.getCurrentQuestion().question?:""
        } else {
            quizAndTestViewModel.stateQuestionInput = ""
        }
        quizAndTestViewModel.getListAnswer()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 500.dp)
            .background(FourthColor)
            .clip(RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        QuestionHeader(index = 0) //fake Index
        Spacer(modifier = Modifier.height(10.dp))
        QuestionInput(quizAndTestViewModel) {
            quizAndTestViewModel.stateQuestionInput = it
        }

        LazyVerticalGrid(
            modifier = Modifier
                .padding(10.dp, 10.dp, 10.dp, 0.dp)
                .height(300.dp),
            cells = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            val listAnswer = quizAndTestViewModel.stateListAnswer
            listAnswer?.let {
                listAnswer.forEachIndexed { index, answer ->
                    item() {
                        AnswerItem(answer = answer.answer?:"", status = answer.status?:false) {
                            quizAndTestViewModel.switchAddingAnswer(index)
                        }
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .padding(end = 10.dp),
            onClick = {quizAndTestViewModel.addQuestion(quizId)},
            contentPadding = PaddingValues(35.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
        ) {
            Text(
                text = "Save",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
    val indexCurrentAnswer = quizAndTestViewModel.stateIndexCurrentAnswer
    if(indexCurrentAnswer != null) {
        AddQuestionPopup(indexCurrentAnswer, quizAndTestViewModel)
    }
}

@Composable
fun QuestionHeader(
    index:Int?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = ThirdColor)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = "Question ${index?:0}",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp),
            onClick = {},
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 7.dp,
                end = 10.dp,
                bottom = 7.dp
            ),
            colors = ButtonDefaults.buttonColors(backgroundColor = RedColor)
        ) {
            Text(
                text = "X",
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
    }

}

@Composable
fun QuestionInput(
    quizAndTestViewModel: QuizAndTestViewModel,
    onChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(10.dp)
            .onFocusEvent {
//                        focusState ->
//                    when {
//                        focusState.hasFocus -> {
//                            stateValidate = value != ""
//                        }
//                    }
            }
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
        ,
        value = quizAndTestViewModel.stateQuestionInput,
        onValueChange = onChange ,
        textStyle = TextStyle(fontSize = 18.sp),
        singleLine = false,
        maxLines = 4,
        placeholder = {
            Text(
                text = "Text question here",
                fontSize = 17.sp,
                color = Gray300
            )
        }
    )
}

@Composable
fun AnswerItem(
    answer:String,
    status:Boolean,
    onClick:()->Unit
) {
    val focusManager = LocalFocusManager.current
    var borderColor = PrimaryColor
    if(status) {
        borderColor = GreenColor700
    }
    Box(
        modifier = Modifier
            .size(135.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(3.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable {
                focusManager.clearFocus()
                onClick()
            }
    ) {
        if(answer=="") {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Tap to add answer",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                text = answer,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun AddQuestionPopup(
    index:Int,
    quizAndTestViewModel: QuizAndTestViewModel
) {
    val currentAnswer = quizAndTestViewModel.getCurrentAnswer()
    var stateAnswer by rememberSaveable { mutableStateOf(currentAnswer.answer?:"") }
    var stateStatus by rememberSaveable { mutableStateOf(currentAnswer.status?:false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                quizAndTestViewModel.addAnswer(stateAnswer, stateStatus)
                quizAndTestViewModel.switchAddingAnswer(null)
//                Log.d("state answer", quizAndTestViewModel.stateListAnswer[1].answer?:"2")
            }
            .background(ModalColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = "Add answer",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .onFocusEvent {}
                    .background(Color.White)
                    .clip(RoundedCornerShape(10.dp))
                    .border(2.dp, PrimaryColor, RoundedCornerShape(10.dp))
                ,
                value = stateAnswer?:"",
                onValueChange = { stateAnswer = it },
                textStyle = TextStyle(fontSize = 18.sp),
                singleLine = false,
                maxLines = 4,
                placeholder = {
                    Text(text = "Text answer here")
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(15.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryColor)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Correct answer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Switch(
                        checked = stateStatus?:false,
                        onCheckedChange = {
                            if(!stateStatus) stateStatus = it
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}


@ExperimentalComposeUiApi
@Preview(showSystemUi = true)
@Composable
fun QuestionBoxPreview() {
//    QuestionBox(index = 1)
}