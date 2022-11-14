package com.intern.evtutors.composes.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.intern.evtutors.R

import com.intern.evtutors.view_models.ProfileViewModel
import com.miggue.mylogin01.ui.theme.*

@Composable
fun CertificateItemTeacher(
    urlImage:String?,
    name:String?,
    date:String?
) {
    val shape = RoundedCornerShape(20.dp)
    FatherOfAppsTheme {
        Surface(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .background(Color.White)
                .clip(shape)
                .border(
                    BorderStroke(1.dp, color = Color(0xFFC1CEF8)),
                    shape = shape
                ),

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                Image(
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight()
                        .clip(shape = shape),
                    painter = rememberAsyncImagePainter(urlImage), //certificate anh1
                    contentDescription = "Uploaded image"
                )
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxHeight()

                        .padding(10.dp, 5.dp)
                ) {
                    Content(icon = painterResource(id = R.drawable.icon_group), title = "Name", content = name)
                    Content(icon = painterResource(id = R.drawable.icon_schedules), title = "Date of Issue", content = date)

                }
            }
        }
    }
}

@Composable
fun Content(
    icon: Painter,
    title:String?,
    content:String?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),

        Arrangement.Top


    ) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            Arrangement.Start,
            Alignment.CenterVertically)

            {
            Icon(
                painter = icon,
                contentDescription = "Content's icon",
                tint = PrimaryColor,
                modifier = Modifier
                    .size(13.dp)
                    .padding(bottom = 2.dp)
            )
            Text(
                text = "$title:",
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = PrimaryColor,
                modifier = Modifier.padding(3.dp, 0.dp)
            )
        }

        Text(
            text = "$content",
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            color = PrimaryColor,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}

