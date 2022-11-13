package com.intern.evtutors.composes.profile

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.intern.evtutors.view_models.ProfileViewModel
import com.miggue.mylogin01.ui.theme.*

@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    TypeOfUser:Int,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    profileViewModel.getUser()
    FatherOfAppsTheme {
        when(TypeOfUser) {
            2 -> {
                Surface() {
                    Column() {
                        var user = profileViewModel.localUser
                        Text(text = "Count: ${user?.roleID}")
                    }
                }
            }

            3 -> {
                TutorInfoPage(
                    profileViewModel
                )
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorInfoPage(
    profileViewModel: ProfileViewModel
) {
    val user = profileViewModel.localUser
    FatherOfAppsTheme {
        Scaffold(
            content = {
                LazyColumn(
                    modifier = Modifier
                        .padding(
                            top = 5.dp,
                            start = 10.dp,
                            end = 10.dp
                        )
                        .background(Color.White)
                ) {
                    stickyHeader{
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .background(Color.White),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            user?.let {
                                TutorInfoHeader(user.id, profileViewModel, profileViewModel.stateUpdating) {
                                    profileViewModel.toggleUpdating()
                                }
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "${user.name}",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center
                                )
//                            var newCertificates = profileViewModel.certificates
//                            newCertificates.remove("")
//                            Text(text = "Number of certificate: ${newCertificates.size}")
                                Text(text = "Number of certificate: ${profileViewModel.userCertificates.size}/5")

                            }

                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.Gray)
                            )
                        }

                    }
                    val userCertificates = profileViewModel.userCertificates
                    if(userCertificates.isNotEmpty()) {
                        var itemCertificate = userCertificates.size
                        items(count = itemCertificate) {
                                index ->
                            val certificate = userCertificates[index]
                            if(certificate!=null) {
                                CertificateItem(certificate, profileViewModel)
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    } else {
                        if (profileViewModel.stateInitialLoading) {
                            item{
                                Text(text = "Loading...")
                                user?.let {
                                    profileViewModel.getUserCertificates(user.id)
                                }
                            }
                        } else {
                            item {
                                Text(text = "You've had nothing!")
                            }
                        }

                    }
                }

                if(profileViewModel.currentCertificate != null) {
                    CertificatesInfo(1, profileViewModel)
                }
                if(profileViewModel.stateAdding) {
                    CertificatesAdding(profileViewModel = profileViewModel)
                }
                if(profileViewModel.stateConfirmSave) {
                    ConfirmSaveBox(profileViewModel = profileViewModel)
                }
                if(profileViewModel.stateConfirmCancel) {
                    ConfirmCancelBox(profileViewModel = profileViewModel)
                }
            },
            bottomBar = {
//                Must fix: handle arrived of add certificate button
                if(profileViewModel.stateUpdating) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 70.dp, end = 20.dp)
                    ) {
                        OutlinedButton(
                            onClick = { profileViewModel.toggle() },
                            modifier = Modifier
                                .size(70.dp)
                                .align(Alignment.BottomEnd),  //avoid the oval shape
                            shape = CircleShape,
                            border = BorderStroke(1.dp, Color.Blue),
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = ItemColor_1),
                        ) {
                            Icon(
                                Icons.Default.AddCircle, contentDescription = "content description"
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun ConfirmSaveBox(
    profileViewModel: ProfileViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ModalColor)
            .padding(
                top = 5.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Confirm all change?")
            Row {
                Button(onClick = {
                    if(profileViewModel.stateChanging) {
                        val user = profileViewModel.localUser
                        user?.let {
                            profileViewModel.putCertificate()
                        }
                        profileViewModel.toggleSaving()
                    }
                }) {
                    Text(
                        text = "Yes"
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = {
                    profileViewModel.toggleSaving()
                }) {
                    Text(
                        text = "No"
                    )
                }
            }
        }
    }
}

@Composable
fun ConfirmCancelBox(
    profileViewModel: ProfileViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ModalColor)
            .padding(
                top = 5.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Are you sure removing all change?")
            Row {
                Button(onClick = {
                    val user = profileViewModel.localUser
                    user?.let {
                        profileViewModel.clearCertificate(user.id)
                    }
                    profileViewModel.toggleCancellation()
                }) {
                    Text(
                        text = "Yes"
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = {
                    profileViewModel.toggleCancellation()
                }) {
                    Text(
                        text = "No"
                    )
                }
            }
        }
    }
}


@Composable
fun TutorInfoHeader(
    idTutor:Int,
    profileViewModel: ProfileViewModel,
    currentUpdating:Boolean,
    onToggleUpdate:(Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier.width(100.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .background(Color.Blue)
                        .align(Alignment.CenterStart),
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Certificate Icon",
                    tint = Color.White
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = "Merit of:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            if(currentUpdating) {
                Box(
                    modifier = Modifier.width(140.dp)
                ) {
                    var isEnable = false
                    if(profileViewModel.stateChanging) {
                        isEnable = true
                    }
                    Button(
                        onClick = {
                            profileViewModel.toggleSaving()
                        },
                        contentPadding = PaddingValues(
                            start = 15.dp,
                            top = 7.dp,
                            end = 15.dp,
                            bottom = 7.dp
                        ),
                        enabled = isEnable
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 12.sp
                        )
                    }

                    Button(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = {
                            if(profileViewModel.stateChanging)
                                profileViewModel.toggleCancellation() //turn off pop up with request
                            else
                                profileViewModel.toggleUpdating() //turn off pop up without request
                        },
                        contentPadding = PaddingValues(
                            start = 15.dp,
                            top = 7.dp,
                            end = 15.dp,
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
            } else {
                if(profileViewModel.userCertificates != null) {
                    if(profileViewModel.userCertificates.size > 0) {
                        Button(
                            onClick = {onToggleUpdate(currentUpdating)},
                            contentPadding = PaddingValues(
                                start = 15.dp,
                                top = 7.dp,
                                end = 15.dp,
                                bottom = 7.dp
                            )
                        ) {
                            Text(text = "Update")
                        }
                    }
                }
            }
        }
    }
}
