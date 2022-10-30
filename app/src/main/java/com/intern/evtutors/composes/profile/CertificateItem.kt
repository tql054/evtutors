package com.intern.evtutors.composes.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.intern.evtutors.common.DataLocal
import com.intern.evtutors.view_models.ProfileViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import com.miggue.mylogin01.ui.theme.PrimaryColor
import com.miggue.mylogin01.ui.theme.RedColor

@Composable
fun CertificateItem(
    index:Int,
    certificate:String,
    url: String,
    profileViewModel: ProfileViewModel
) {
    val shape = RoundedCornerShape(25.dp)
    FatherOfAppsTheme {
        Surface(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .clip(shape)
                .border(
                    BorderStroke(1.dp, color = PrimaryColor),
                    shape = shape
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PrimaryColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .wrapContentHeight(Alignment.CenterVertically)
                        ,
                        text = "Certificate $certificate",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        textAlign = TextAlign.Left
                    )

                    if(profileViewModel.stateUpdating) {
                        Button(
                            modifier = Modifier.height(20.dp),
                            onClick = {profileViewModel.deleteCertificate(index)},
                            contentPadding = PaddingValues(
                                start = 15.dp,
                                end = 15.dp,
                            ),
                            colors = ButtonDefaults.buttonColors(backgroundColor = RedColor)
                        ) {
                            Text(
                                text = "Delete",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                }

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(shape = shape),
                    painter = rememberAsyncImagePainter(DataLocal.BUCKET_API + url),
                    contentDescription = "Uploaded image"
                )
            }
        }

    }
}




@ExperimentalComposeUiApi
@Preview
@Composable
fun CertificatePreview() {
    FatherOfAppsTheme {
//        CertificateItem(
//            "abc",
//            "https://ev-certificate-images.s3.us-east-2.amazonaws.com/87f41f44849fb91c85d4be49f894990d.jpg",
//        true
//        )
    }
}
