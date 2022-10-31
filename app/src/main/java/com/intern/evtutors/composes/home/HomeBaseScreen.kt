package com.intern.evtutors.composes.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.intern.evtutors.data.model_json.CourseJson
import com.intern.evtutors.data.models.Course
import com.intern.evtutors.data.models.Teacher
import com.intern.evtutors.ui.customer.login.LoginViewModel
import com.intern.evtutors.view_models.HomeViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBaseScreen(
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
                    StudentHomePage(users, tutors)
                }

                3 -> {
//                    TutorHomePage(users, tutors)
                    viewModel.fetchData()
                    val users by viewModel.course.observeAsState(arrayListOf())
                    val tutors by viewModel.tutors.observeAsState(arrayListOf())
                    TutorHomePage(users, tutors)

                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudentHomePage(
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
            HomeTutors(tutors = tutors)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorHomePage(
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
            HomeTutors(tutors = tutors)
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
    tutors:List<Teacher>
) {
    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .fillMaxWidth()) {
        for (tutor in tutors){
            TeacherItem(tutor)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}
