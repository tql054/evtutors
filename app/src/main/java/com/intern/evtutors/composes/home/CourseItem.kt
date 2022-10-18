package com.intern.evtutors.composes.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intern.evtutors.R
import com.intern.evtutors.data.models.Course


@Composable
fun CourseItem (
    modifier:Modifier = Modifier,
    course: Course,
    color:Int
) {
    var bg = Color.Blue
    val shape = RoundedCornerShape(15.dp)
    if(color==1) {
        bg = Color.Magenta
    }
    Surface(
        modifier = Modifier
            .height(100.dp)
            .width(150.dp)
            .clip(shape = shape)
    ) {
        Column(
            modifier = Modifier
                .background(color = bg)
                .padding(5.dp)
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
//            Course information
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .defaultMinSize(50.dp, 15.dp)
                        .clip(shape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "25 Lessons",
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        )
                }
                Box(
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                        .defaultMinSize(50.dp, 15.dp)
                        .clip(shape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp),
                        text = course.tutorFee,
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        )
                }
            }
//            Name of course
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = course.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                color = Color.White
            )

//            Teacher information
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircleAvatar()
                Spacer(modifier = Modifier.width(5.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
//                    Teacher rating
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ){
                        Text(
                            text = "Teacher",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.width(2.dp))
                        InfoButton(imageVector = Icons.Default.Star, title = "4.5")
                        Spacer(modifier = Modifier.width(2.dp))
                        InfoButton(imageVector = Icons.Default.KeyboardArrowRight, title = "View profile" )
                    }
                    
//                    Teacher name
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "${course.idTeacher}",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Left
                    )
                }
            }
        }
    }

}

@Composable
fun CircleAvatar() {
    Surface(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, color = Color.White),
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .size(35.dp, 35.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_spaceship_launch),
            contentDescription = "User's avatar",
            contentScale = ContentScale.Inside
            )
    }


}

@Composable
fun InfoButton(
    imageVector: ImageVector,
    title : String
) {
    val shape = RoundedCornerShape(25.dp)
    Box(modifier = Modifier
        .background(color = Color.White)
//        .size(30.dp, 15.dp)
        .clip(shape)) {
        Row(modifier = Modifier
            .padding(start = 2.dp, end = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .size(10.dp),
                imageVector = imageVector,
                contentDescription = title,
                tint = Color.Red
            )
            Text(
                text = title,
                fontSize = 4.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


