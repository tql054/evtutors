package com.intern.evtutors.composes.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.intern.evtutors.data.models.Course
import com.intern.evtutors.data.models.Teacher
import com.intern.evtutors.view_models.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBaseScreen(
    navHostController: NavHostController,
    TypeOfUser:Int,
    viewModel: HomeViewModel = hiltViewModel()

) {
    LazyColumn() {
        stickyHeader {
            HomeHeader()
        }
        item {
            when(TypeOfUser) {
                2 -> {
                    viewModel.fetchData()
                    val users by viewModel.course.observeAsState(arrayListOf())
                    val tutors by viewModel.tutors.observeAsState(arrayListOf())
                    StudentHomePage(navHostController,users, tutors)
                }

                3 -> {
//                    TutorHomePage(users, tutors)
                    viewModel.fetchData()
                    val users by viewModel.course.observeAsState(arrayListOf())
                    val tutors by viewModel.tutors.observeAsState(arrayListOf())
                    TutorHomePage(navHostController,users, tutors)

                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudentHomePage(
    navHostController: NavHostController,
    courses:List<Course>,
    tutors:List<Teacher>
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
            verticalArrangement = Arrangement.Center,
        ) {
            HomeTopic(title = "Available courses")
            Spacer(modifier = Modifier.padding(top = 5.dp))
            HomeCourse(courses)
            Spacer(modifier = Modifier.padding(top = 10.dp))
            HomeTopic(title = "Today's tutors")
            Spacer(modifier = Modifier.padding(top = 5.dp))
            HomeTutors(tutors = tutors,navHostController)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorHomePage(
    navHostController: NavHostController,
    courses:List<Course>,
    tutors:List<Teacher>
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
            verticalArrangement = Arrangement.Center,
        ) {
            HomeTopic(title = "Your offers")
            Spacer(modifier = Modifier.padding(top = 5.dp))
            HomeCourse(courses)
            Spacer(modifier = Modifier.padding(top = 10.dp))
            HomeTopic(title = "Available offers")
            Spacer(modifier = Modifier.padding(top = 5.dp))
            HomeTutors(tutors = tutors,navHostController)
        }
    }
}

@Composable
fun HomeTopic(
    title:String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        ClickableText(
            text = AnnotatedString("See all" ),
            onClick = {})
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeCourse(
    courses:List<Course>
) {
    LazyVerticalGrid(
        modifier = Modifier.height(230.dp),
        cells = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        var itemCourse = courses.size
        items(count = itemCourse) {
                index ->
            val course = courses[index]
//            Text(text = course.name)
            if(index%3==0) {
                CourseItem(course = course, color = 1)
            } else {
                CourseItem(course = course, color = 0)
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTutors (
    tutors:List<Teacher>,
    navHostController: NavHostController
) {
    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .fillMaxWidth()) {
        for (tutor in tutors){
            TeacherItem(tutor,navHostController)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}
