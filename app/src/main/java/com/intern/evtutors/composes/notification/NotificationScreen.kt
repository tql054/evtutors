package com.intern.evtutors.composes.notification

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.services.s3.AmazonS3Client
import com.intern.evtutors.common.DataLocal
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


@Composable
fun NotificationScreen(

) {
     FatherOfAppsTheme {
          CertificateUploading()
     }
}

@Composable
fun CertificateUploading() {
     var creds: BasicAWSCredentials = BasicAWSCredentials(DataLocal.ACCESS_ID, DataLocal.SECRET_KEY)
     var s3Client: AmazonS3Client = AmazonS3Client(creds)
     var filePath by rememberSaveable() {
          mutableStateOf<Uri?>(null)
     }
     val context = LocalContext.current
     val bitmap = rememberSaveable {
          mutableStateOf<Bitmap?>(null)
     }

     val launcher = rememberLauncherForActivityResult(
          contract = ActivityResultContracts.GetContent()) {
               uri:Uri? -> filePath = uri
     }

     Column() {
          Button(onClick = { launcher.launch("image/*") }) {
               Text(text = "Upload")
          }

          Spacer(modifier = Modifier.height(12.dp))

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
                    Image(bitmap = it.asImageBitmap(),
                         contentDescription = "Uploaded Image",
                         modifier = Modifier.size(400.dp)
                    )
               }
          }
     }

     fun uploadImage() {
//          val inputStream: InputStream? = contentResolver.openInputStream(filePath!!)
          val file = File.createTempFile("image",filePath!!.lastPathSegment)
          val outStream: OutputStream = FileOutputStream(file)
//          outStream.write(inputStream!!.readBytes())
          val trans = TransferUtility.builder().context(context).s3Client(s3Client).build()
          val observer: TransferObserver = trans.upload(DataLocal.BUCKET_NAME,filePath!!.lastPathSegment, file)//manual storage permission
          observer.setTransferListener(object : TransferListener {
               override fun onStateChanged(id: Int, state: TransferState) {
                    if (state == TransferState.COMPLETED) {
                         Log.d("msg","success")
                    } else if (state == TransferState.FAILED) {
                         Log.d("msg","fail")
                    }
               }
               override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                    if(bytesCurrent == bytesTotal){
//                         imageView!!.setImageResource(R.drawable.upload_image_with_round)
                    }
               }
               override fun onError(id: Int, ex: Exception) {
                    Log.d("error",ex.toString())
               }
          })
     }
}


