package com.intern.evtutors.composes.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intern.evtutors.data.models.Course
import com.intern.evtutors.view_models.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBaseScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier : Modifier = Modifier
) {
    viewModel.fetchData()
    val users by viewModel.course.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    Column(modifier = Modifier.fillMaxWidth()) {
        HomeHeader()
        HomeCourses(users)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeCourses(
    courses:List<Course>
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 20.dp,
                bottom = 20.dp
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Available Courses",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
                ClickableText(
                    text = AnnotatedString("See all" ),
                    onClick = {})
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            HomeCourse(courses)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeCourse(
    courses:List<Course>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
        var itemCourse = courses.size
            items(count = itemCourse) {
                index ->
            val course = courses[index]
//            Text(text = course.name)
            CourseItem(course = course)
        }
    }
}




