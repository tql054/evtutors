package com.intern.evtutors.composes.profile

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.google.android.material.datepicker.MaterialDatePicker
import com.intern.evtutors.data.model_json.CertificateJson
import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.view_models.CertificatesViewModel
import com.intern.evtutors.view_models.ProfileViewModel
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import com.miggue.mylogin01.ui.theme.ModalColor
import com.miggue.mylogin01.ui.theme.Red500
import com.miggue.mylogin01.ui.theme.RedColor
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CertificatesAdding(
    profileViewModel: ProfileViewModel,
    certificatesViewModel: CertificatesViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    var stateName by rememberSaveable {
        mutableStateOf<String>("")
    }

    var stateAddress by rememberSaveable {
        mutableStateOf<String>("")
    }

    var stateDOI by rememberSaveable {
        mutableStateOf<String>("Date of issue")
    }

    var stateDOE by rememberSaveable {
        mutableStateOf<String>("Date of expiration")
    }

    var certificates = certificatesViewModel.certificates

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ModalColor),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .width(500.dp)
                .fillMaxHeight()
                .align(Alignment.Center)
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoInput(title = "What's certificate?", value = stateName, certificatesViewModel = certificatesViewModel) {
                stateName = it
                checkValidData(
                    stateName,
                    stateAddress,
                    stateDOI,
                    stateDOE,
                    certificatesViewModel
                )
            }

            InfoInput(title = "Place granted?", value = stateAddress, certificatesViewModel = certificatesViewModel) {
                stateAddress = it
                checkValidData(
                    stateName,
                    stateAddress,
                    stateDOI,
                    stateDOE,
                    certificatesViewModel
                )
            }

            DatePickerview(stateDOI) {
                stateDOI = it
                checkValidData(
                    stateName,
                    stateAddress,
                    stateDOI,
                    stateDOE,
                    certificatesViewModel
                )
            }

            DatePickerview(stateDOE) {
                stateDOE = it
                checkValidData(
                    stateName,
                    stateAddress,
                    stateDOI,
                    stateDOE,
                    certificatesViewModel
                )
            }

            if (certificates.isEmpty()) {
                Text(
                    text = "Please upload at least one and no more five images",
                    fontSize = 10.sp
                )
            } else {
                Column {
                    Text(
                        text = "Number of image: ${certificates.size}/5",
                        fontSize =15.sp
                    )
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .fillMaxWidth()
                    ) {
                        for (certificate in certificates) {
                            Image(
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(130.dp)
                                    .clip(shape = RoundedCornerShape(15.dp)),
                                painter = rememberAsyncImagePainter(certificate),
                                contentDescription = "Uploaded image"
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }

            if(certificates.size<5 || certificates.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        certificatesViewModel.stateUpload = true
                        focusManager.clearFocus()
                    }) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Image icon"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                if(certificatesViewModel.stateValidData) {
                    Button(onClick = {
                        if(certificatesViewModel.checkValidDate(stateDOI, stateDOE)) {
                            certificatesViewModel.stateErrorBox = true
                        } else {
                            val certificates = certificatesViewModel.certificates
                            val newCertificates = generateCertificateJson(stateName, stateAddress, 1, stateDOI, stateDOE, certificates)
                            profileViewModel.addCertificate(newCertificates)
                            profileViewModel.toggle()
                        }

                    }) {
                        Text(
                            text = "Add"
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }

                Button(onClick = {
                    certificatesViewModel.certificates = mutableListOf<String>()
                    profileViewModel.toggle()
                }) {
                    Text(
                        text = "Cancel"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Note: Thông tin bằng cấp và hình ảnh cần phải được cung cấp chính xác 100%",
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                color = RedColor
            )

        }
        if (certificatesViewModel.stateUpload) {
            CertificateImageUpload(certificatesViewModel = certificatesViewModel)
        }
        if(certificatesViewModel.stateErrorBox) {
            ErrorBox(message = "Opps! The expiration date is too soon!", certificatesViewModel = certificatesViewModel)
        }
    }
}

@Composable
fun CertificatesInfo(
    id:Int,
    profileViewModel: ProfileViewModel
) {
    var certificateJson = profileViewModel.currentCertificate
    profileViewModel.setCurrentCertificate(id)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ModalColor)
            .padding(
                top = 5.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Column (
            modifier = Modifier
                .width(500.dp)
                .align(Alignment.Center)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoItem(title = "Tên bằng", content = certificateJson!!.name)
            InfoItem(title = "Nơi cấp", content = certificateJson.placeOfIssue)
            InfoItem(title = "Ngày cấp", content = certificateJson.dateOfIssue)
            InfoItem(title = "Ngày cấp", content = certificateJson.dateOfExpiry)
            CertificatesSlicer(profileViewModel)
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Button(onClick = {
                    profileViewModel.deleteCertificate(certificateJson.id)
                    profileViewModel.currentCertificate = null
                }) {
                    Text(
                        text = "Delete",
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = {profileViewModel.currentCertificate = null}) {
                    Text(
                        text = "Cancel"
                    )
                }
            }
        }
    }
}

@Composable
fun DatePickerview(
    datePicked : String,
    updatedDate : ( date : String ) -> Unit,
) {
    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val focusManager = LocalFocusManager.current
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            focusManager.clearFocus()
            if(mDayOfMonth < 10 && mMonth+1 < 10) updatedDate("0$mDayOfMonth-0${mMonth+1}-$mYear")
            if(mDayOfMonth < 10 && mMonth+1 >= 10) updatedDate("0$mDayOfMonth-${mMonth+1}-$mYear")
            if (mDayOfMonth >= 10 && mMonth+1 < 10) updatedDate("$mDayOfMonth-0${mMonth+1}-$mYear")
            if(mDayOfMonth > 10 && mMonth+1 > 10)  updatedDate("$mDayOfMonth-${mMonth+1}-$mYear")
//            updatedDate("$mDayOfMonth-${mMonth+1}-$mYear")
        }, mYear, mMonth, mDay
    )

    Box(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp)
            .border(
                1.dp,
                MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                RoundedCornerShape(5.dp)
            )
            .clickable {
                mDatePickerDialog.show()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .padding(start = 15.dp, bottom = 2.dp),
                imageVector = Icons.Default.DateRange,
                contentDescription = "Icon Date Picker",
                tint = Color.Black,
            )
            Text(
                text= datePicked,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 15.dp,
                        bottom = 15.dp,
                        start = 5.dp,
                        end = 15.dp
                    ),
            )
        }
    }
}


@Composable
fun CertificateImageUpload(
    certificatesViewModel: CertificatesViewModel
) {
    var filePath by rememberSaveable {
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
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Column (
            modifier = Modifier
                .width(500.dp)
                .align(Alignment.Center)
                .clip(shape = RoundedCornerShape(15.dp))
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
                    Column {
                        Image(bitmap = it.asImageBitmap(),
                            contentDescription = "Uploaded Image",
                            modifier = Modifier.size(400.dp)
                        )
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

            Row {
                if(filePath!=null) {
                    if(certificatesViewModel.stateLoadingImage) {
                        Button(onClick = {}, enabled = false)
                        {
                            CircularProgressIndicator(
                                modifier = Modifier.size(17.dp),
                                color = Color.White
                            )
                        }
                    } else {
                        Button(onClick = {
                            certificatesViewModel.stateLoadingImage = true
                            onUpload(filePath!!, context, certificatesViewModel)
                        }) {
                            Text(
                                text = "Add",
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                }
                Button(onClick = { certificatesViewModel.stateUpload = false }) {
                    Text(
                        text = "Cancel"
                    )
                }
            }
        }
    }
}

@Composable
fun CertificatesSlicer(
    profileViewModel: ProfileViewModel
) {
    val results = mutableListOf<String>(
        profileViewModel.currentCertificate!!.img1,
        profileViewModel.currentCertificate!!.img2,
        profileViewModel.currentCertificate!!.img3,
        profileViewModel.currentCertificate!!.img4
    )
    if (results.isEmpty()) {

    } else {
        Row(modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()) {
            for (certificate in results){
                if (certificate != "") {
                    Image(
                        modifier = Modifier
                            .width(200.dp)
                            .height(150.dp)
                            .clip(shape = RoundedCornerShape(15.dp)),
                        painter = rememberAsyncImagePainter(certificate),
                        contentDescription = "Uploaded image"
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }

}

@Composable
fun InfoItem(
    title:String,
    content: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            fontSize = 9.sp
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = content,
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Left,
            color = RedColor
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InfoInput(
    certificatesViewModel: CertificatesViewModel,
    title:String,
    value:String,
    onChange:(String) -> Unit
) {
    var stateValidate by rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    when {
                        focusState.hasFocus -> {
                            stateValidate = value != ""
                        }
                    }
                }
            ,
            value = value,
            onValueChange = onChange,
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.Gray,
                disabledTextColor = Color.Black
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(text = title)
            }
        )
        if(!stateValidate) {
            Text(
                text = "This one is not allowed empty",
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                color = Red500
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun CircularLoadingIcon(isDisplayed: Boolean, verticalBias: Float) {

        CircularProgressIndicator(
            color = MaterialTheme.colors.primary
        )
}

@Composable
fun ErrorBox(
    message:String,
    certificatesViewModel: CertificatesViewModel
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
            Text(text = message)
            Button(onClick = {
                certificatesViewModel.stateErrorBox = false
            }) {
                Text(
                    text = "OK"
                )
            }
        }
    }
}


fun onUpload(filePath:Uri, context:Context, certificatesViewModel: CertificatesViewModel) {
    val TAG = "Cloudinary log: "
    MediaManager.get().upload(filePath).callback(object : UploadCallback {
        override fun onStart(requestId: String) {
            Log.d(TAG, "onStart: " + "started")
        }

        override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
            Log.d(TAG, "onStart: " + "uploading")
        }

        override fun onSuccess(requestId: String, resultData: Map<*, *>?) {
            Log.d(TAG, "onStart: " + "usuccess")
            certificatesViewModel.addImageCertificate(resultData!!.get("secure_url").toString())
            certificatesViewModel.stateLoadingImage = false
        }

        override fun onError(requestId: String?, error: ErrorInfo) {
            Log.d(TAG, "onStart: $error")
        }

        override fun onReschedule(requestId: String?, error: ErrorInfo) {
            Log.d(TAG, "onStart: $error")
        }
    }).dispatch()
}

private fun checkValidData(
    name:String,
    address:String,
    doi:String,
    doe:String,
    certificatesViewModel: CertificatesViewModel
) {
    certificatesViewModel.stateValidData = name!="" && address!="" && doi!="Date of issue" && doe!="Date of expiration" && certificatesViewModel.certificates.isNotEmpty()
}

private fun generateCertificateJson(
    name:String, address:String, status:Int, doi:String, doe:String, certificate:MutableList<String>
):CertificateJson {
    var newCertificateJson = CertificateJson(1, name, address, doi, doe, status, "", "", "", "", "")
    when(certificate.size) {
        1 -> {
            newCertificateJson = CertificateJson(1, name, address, doi, doe, status, certificate[0], "", "", "", "")
        }

        2 -> {
            newCertificateJson = CertificateJson(1, name, address, doi, doe, status, certificate[0], certificate[1], "", "", "")
        }

        3 -> {
            newCertificateJson = CertificateJson(1, name, address, doi, doe, status, certificate[0], certificate[1], certificate[2], "", "")
        }

        4 -> {
            newCertificateJson = CertificateJson(1, name, address, doi, doe, status, certificate[0], certificate[1], certificate[2], certificate[3], "")
        }

        5 -> {
            newCertificateJson = CertificateJson(1, name, address, doi, doe, status, certificate[0], certificate[1], certificate[2], certificate[3], certificate[4])
        }
    }
    return newCertificateJson
}