package com.intern.evtutors.composes.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.*
import com.amazonaws.services.s3.AmazonS3Client
import com.intern.evtutors.common.DataLocal
import com.intern.evtutors.composes.home.CourseItem
import com.intern.evtutors.ui.customer.login.LoginViewModel
import com.intern.evtutors.view_models.ProfileViewModel
import com.intern.evtutors.view_models.UserViewModel
import com.miggue.mylogin01.ui.theme.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

@Composable
fun ProfileScreen(
    TypeOfUser:Int,
    viewModel: LoginViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    viewModel.getuser()
    FatherOfAppsTheme {
        when(TypeOfUser) {
            2 -> {
                Surface() {
                    Column() {
                        var user = viewModel.user
                        Text(text = "Count: ${user.name}")
                    }
                }
            }

            3 -> {
                TutorInfoPage(
                    profileViewModel,
                    viewModel
                )
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorInfoPage(
    profileViewModel: ProfileViewModel,
    viewModel:LoginViewModel,
) {
    val user = viewModel.user
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
                            val number = profileViewModel.certificates.size
                            Text(text = "Number of certificate: $number")
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.Gray)
                            )
                        }

                    }
                    val certificates = profileViewModel.certificates
                    if(certificates.isNotEmpty()) {
                        var itemCertificate = certificates.size
                        items(count = itemCertificate) {
                                index ->
                            val certificate = certificates[index]
                            if(certificate!="") {
                                CertificateItem(index, certificate = "$index", url = certificate!!, profileViewModel = profileViewModel )
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    } else {
                        item{
                            Text(text = "Loading")
                            profileViewModel.fetchCetificate(1234)
                        }
                    }
                }

                if(profileViewModel.stateAdding) {
                    uploadImage(profileViewModel)
                }
                if(profileViewModel.stateConfirmSave) {
                    ConfirmSaveBox(profileViewModel = profileViewModel)
                }
                if(profileViewModel.stateConfirmCancel) {
                    ConfirmCancelBox(profileViewModel = profileViewModel)
                }

            },
            bottomBar = {
                if(profileViewModel.stateUpdating && profileViewModel.certificates[4]=="") {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 70.dp, end = 20.dp)
                    ) {
                        OutlinedButton(onClick = {profileViewModel.toggle()},
                            modifier= Modifier
                                .size(70.dp)
                                .align(Alignment.BottomEnd),  //avoid the oval shape
                            shape = CircleShape,
                            border= BorderStroke(1.dp, Color.Blue),
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.outlinedButtonColors(contentColor =  ItemColor_1),
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
                        profileViewModel.putCertificate(1234)
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
                    profileViewModel.clearCertificate()
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
                    fontSize = 14.sp,
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
                        onClick = {profileViewModel.toggleCancellation()},
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

@Composable
fun uploadImage(
//    filePath: Uri
    profileViewModel: ProfileViewModel
) {
    var filePath by rememberSaveable() {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) {
            uri: Uri? -> filePath = uri
    }
    val context = LocalContext.current.applicationContext
    val bitmap = rememberSaveable {
        mutableStateOf<Bitmap?>(null)
    }
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
            filePath?.let {
                if(Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let {
                    Column() {
                        Image(bitmap = it.asImageBitmap(),
                            contentDescription = "Uploaded Image",
                            modifier = Modifier.size(400.dp)
                        )
//                        Button(onClick = {uploadImage(filePath!!)}) {
//                            Text(text = "Upload to S3")
//                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
            if(filePath==null) {
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { launcher.launch("image/jpeg") }) {
                    Icon(
                        modifier = Modifier.size(200.dp),
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Image icon")
                }
            }
            
            Row() {
                if(filePath!=null) {
                    Button(onClick = { onUpload(filePath!!, context, profileViewModel) }) {
                        Text(
                            text = "Add",
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Button(onClick = { profileViewModel.toggle() }) {
                    Text(
                        text = "Cancel"
                    )
                }
            }
        }
    }
}

var creds: BasicAWSCredentials = BasicAWSCredentials(DataLocal.ACCESS_ID, DataLocal.SECRET_KEY)
var s3Client: AmazonS3Client = AmazonS3Client(creds)
fun onUpload(
    filePath:Uri,
    context:Context,
    profileViewModel: ProfileViewModel
) {
    val inputStream: InputStream? = context.contentResolver.openInputStream(filePath!!)
    val file = File.createTempFile("image",filePath!!.lastPathSegment + ".jpg")

    val outStream: OutputStream = FileOutputStream(file)
    outStream.write(inputStream!!.readBytes())
    TransferNetworkLossHandler.getInstance(context)

    val resultUrl = filePath!!.lastPathSegment!!.replace(":", "")+".jpg"
    val trans = TransferUtility.builder().context(context.applicationContext).s3Client(s3Client).build()
    val observer: TransferObserver = trans.upload(DataLocal.BUCKET_NAME, resultUrl, file)

    profileViewModel.toggle()
    observer.setTransferListener(object : TransferListener {
//        Must be handled enabling the adding button until successfully uploading
        override fun onStateChanged(id: Int, state: TransferState) {
            if (state == TransferState.COMPLETED) {
                profileViewModel.addCertificate(resultUrl)
                Log.d("msg","success")
            } else if (state == TransferState.FAILED) {
                Log.d("msg","fail")
            }
        }
        override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {

            if(bytesCurrent == bytesTotal){
//                    imageView!!.setImageResource(R.drawable.upload_image_with_round)
            }
        }
        override fun onError(id: Int, ex: Exception) {
            Log.d("error",ex.toString())
        }
    })
}



@ExperimentalComposeUiApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    FatherOfAppsTheme {
        Text(text = "abc")
//        uploadImage()
    }
}

