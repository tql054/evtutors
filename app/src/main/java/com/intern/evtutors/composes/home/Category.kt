package com.intern.evtutors.composes.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intern.evtutors.activities.App
import com.intern.evtutors.data.models.Course
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import com.miggue.mylogin01.ui.theme.PrimaryColor

@Composable
fun Category(
    modifier: Modifier = Modifier
) {
    val categoryList = listOf(
        "Basic for work",
        "English for conversation",
        "B1-B2 Certificate",
        "C1-C2 Certificate",
        "TOEIC Certificate",
        "TOEFL Certificate",
        "IELTS Certificate"
    )

//    Surface(
//        modifier = Modifier
//            .clip(shape = RoundedCornerShape(25.dp))
//    ) {
//
//    }
    Column(
        modifier = Modifier
            .width(340.dp)
            .padding(
                top = 300.dp,
                end = 20.dp,
                start = 20.dp,
            )
            .background(PrimaryColor),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Category",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        for(category in categoryList) {
            CategoryItem(title = category)
        }
//        LazyColumn(
//            modifier = Modifier.height(600.dp)
//        ) {
//            var size = categoryList.size
//            items(count = size) { index ->
//                val item = categoryList[index]
//
//            }
//        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    title:String
) {
    Surface(
        modifier = Modifier
            .padding(top = 10.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(25.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.White)
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }

}


//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FatherOfAppsTheme {
//        Category()
//    }
//}