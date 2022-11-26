package com.intern.evtutors.composes.lesson.lesson_test

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miggue.mylogin01.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuestionBox(
    index:Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 500.dp)
            .background(FourthColor)
            .clip(RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        QuestionHeader(index = index) //fake Index
        Spacer(modifier = Modifier.height(10.dp))
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
            value = "",
            onValueChange = {  },
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = false,
            maxLines = 4,
            placeholder = {
                Text(
                    text = "placeHolder",
                    fontSize = 17.sp,
                    color = Gray300
                )
            }
        )

        LazyVerticalGrid(
            modifier = Modifier
                .padding(10.dp)
                .height(400.dp),
            cells = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                AnswerItem(
                    answer = "There will be a answer session The respondents " +
                            "were asked two follow-up questions to their ...\n" +
                        "\n"
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(end = 10.dp),
            onClick = { },
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
//        AddQuestionPopup()
    }
}

@Composable
fun QuestionHeader(
    index:Int
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
            text = "Question $index",
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
fun AddQuestionPopup() {
    Box(
        modifier = Modifier
            .fillMaxSize()
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
                    .border(2.dp, PrimaryColor, RoundedCornerShape(10.dp))
                ,
                value = "",
                onValueChange = {  },
                textStyle = TextStyle(fontSize = 18.sp),
                singleLine = false,
                maxLines = 4,
                placeholder = {
                    Text(text = "placeHolder")
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
                        checked = true,
                        onCheckedChange = {  }
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerItem(
    answer:String
) {
    val borderColor = PrimaryColor
    Box(
        modifier = Modifier
            .size(135.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(3.dp, borderColor, RoundedCornerShape(10.dp))
    ) {
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

@ExperimentalComposeUiApi
@Preview(showSystemUi = true)
@Composable
fun QuestionBoxPreview() {
    QuestionBox(index = 1)
}