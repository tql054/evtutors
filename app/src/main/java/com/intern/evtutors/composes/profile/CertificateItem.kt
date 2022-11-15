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
import com.intern.evtutors.data.model_json.CertificateJson
import com.intern.evtutors.view_models.ProfileViewModel
import com.miggue.mylogin01.ui.theme.*

@Composable
fun CertificateItem(
    certificateJson: CertificateJson,
    profileViewModel: ProfileViewModel
) {
    val shape = RoundedCornerShape(20.dp)
    FatherOfAppsTheme {
        Surface(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .background(Color.White)
                .clip(shape)
                .clickable {
                    profileViewModel.currentCertificate  = certificateJson
                }
                .border(
                    BorderStroke(2.dp, color = PrimaryColor),
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
                        .padding(start = 8.dp)
                        .clip(shape = shape),
                    painter = rememberAsyncImagePainter(certificateJson.img1), //certificate anh1
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
                    Content(icon = painterResource(id = R.drawable.icon_group), title = "Name", content = certificateJson.name)
                    Content(icon = painterResource(id = R.drawable.icon_schedules), title = "Date of Issue", content = certificateJson.dateOfIssue)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        StatusBox(status = certificateJson.status)
                        if(profileViewModel.stateUpdating) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_trash),
                                contentDescription = "Content's icon",
                                tint = Red500,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(bottom = 2.dp)
                                    .clickable {
                                        profileViewModel.deleteCertificate(certificateJson.id)
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Content(
    icon: Painter,
    title:String,
    content:String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
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
        Text(
            text = "$content",
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            color = PrimaryColor,
            modifier = Modifier.padding(10.dp, 0.dp)
        )
    }
}

@Composable
fun StatusBox(
    status:Int
) {
    var textColor = Brown700
    var borderColor = Brown500
    var backgroundColor = Brown300
    var content = "Đang chờ duyệt"
    when(status) {
        2 -> {
            textColor = GreenColor700
            borderColor = GreenColor500
            backgroundColor = GreenColor300
            content = "Đã duyệt"
        }
        3 -> {
            textColor = Red700
            borderColor = Red500
            backgroundColor = Red300
            content = "Từ chối duyệt"
        }
    }
    Surface(
        modifier = Modifier
            .size(55.dp, 15.dp)
            .border(1.dp, color = borderColor, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentAlignment = Alignment.Center,

            ) {
            Text(
                modifier = Modifier
                    .padding(10.dp, 4.dp)
                    .fillMaxSize(),
                text = content,
                fontSize = 6.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
    }

}
@Preview
@Composable
fun CertificatePreview() {
    FatherOfAppsTheme {
//        CertificateItem()
    }
}
