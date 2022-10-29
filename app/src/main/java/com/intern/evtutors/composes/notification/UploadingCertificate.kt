package com.intern.evtutors.composes.notification

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.*
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.ResponseHeaderOverrides
import com.intern.evtutors.common.DataLocal
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL


class UploadingCertificate:ComponentActivity() {
    val PICK_IMAGE_REQUEST = 1
    var isUpdated = false
    var creds: BasicAWSCredentials = BasicAWSCredentials(DataLocal.ACCESS_ID, DataLocal.SECRET_KEY)
    var s3Client: AmazonS3Client = AmazonS3Client(creds)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatherOfAppsTheme() {
                var filePath by rememberSaveable() {
                    mutableStateOf<Uri?>(null)
                }
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()) {
                        uri:Uri? -> filePath = uri
                }
                val context = LocalContext.current
                val bitmap = rememberSaveable {
                    mutableStateOf<Bitmap?>(null)
                }

                Column() {
                    Button(onClick = { launcher.launch("image/jpeg") }) {
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
                            Column() {
                                Image(bitmap = it.asImageBitmap(),
                                    contentDescription = "Uploaded Image",
                                    modifier = Modifier.size(400.dp)
                                )
                                Button(onClick = {uploadImage(filePath!!)}) {
                                    Text(text = "Upload to S3")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } catch (e: Exception) {
            Log.e("error: ", "getRealPathFromURI Exception : $e")
            ""
        } finally {
            cursor?.close()
        }
    }

    fun uploadImage(filePath:Uri) {
        val fileString = filePath
//        Log.d("file path", fileString+"")
//        sendImageToAmazonS3Server(fileString!!)

        val inputStream:InputStream? = contentResolver.openInputStream(filePath!!) //to read a file from content path

        val file = File.createTempFile("image",filePath!!.lastPathSegment + ".jpg")
//        Log.d("FIle path: ", filePath!!.lastPathSegment)
        val outStream:OutputStream = FileOutputStream(file)//creating stream pipeline to the file

        outStream.write(inputStream!!.readBytes())//passing bytes of data to the filestream / Write array of byte to current output stream

        val trans = TransferUtility.builder().context(applicationContext).s3Client(s3Client).build()
        val observer: TransferObserver = trans.upload(DataLocal.BUCKET_NAME,filePath!!.lastPathSegment + ".jpg", file)//manual storage permission
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
//                    imageView!!.setImageResource(R.drawable.upload_image_with_round)
                }
            }
            override fun onError(id: Int, ex: Exception) {
                Log.d("error",ex.toString())
            }
        })

    }

    private fun sendImageToAmazonS3Server(Filepath: String): String? {
        //String MY_ACCESS_KEY_ID
        val MY_ACCESS_KEY_ID = "Put Access key"

        //String MY_SECRET_KEY
        val MY_SECRET_KEY = "Put SECRET_KEY "


        //String MY_PICTURE_BUCKET
        val MY_PICTURE_BUCKET = "bucketName"
        //String MY_PICTURE_BUCKET = "wom-profilepics-test";
        val file = File(Filepath)
        return if (file.exists()) {
            val fileExtension = "jpg"
            val imageNAME = System.currentTimeMillis().toString()
            var fileNameToUpload = "$imageNAME.$fileExtension"
            fileNameToUpload = fileNameToUpload.trim { it <= ' ' }
            try {
                val s3Client = AmazonS3Client(BasicAWSCredentials(DataLocal.ACCESS_ID, DataLocal.SECRET_KEY))
                // create bucket
                //s3Client.createBucket(MY_PICTURE_BUCKET);
                val input =
                    URL("https://hi.co/bundles/hitomain/images/hi_big.png?v=1448090952").openStream()
                val objectMetadata = ObjectMetadata()
                val por =
                    PutObjectRequest(DataLocal.BUCKET_NAME, fileNameToUpload, input, objectMetadata)
                por.cannedAcl = CannedAccessControlList.PublicRead
                val result = s3Client.putObject(por)
                Log.d("PATH", "" + fileNameToUpload)

                /*LocalLog.d("putting Object result ",""+ result.getETag()
                                //result.
                                + " MD5 " + result.getContentMd5());*/
                val override = ResponseHeaderOverrides()
                override.contentType = "image/jpeg"

                /*GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest( MY_PICTURE_BUCKET,imageNAME );
                        urlRequest.setExpiration( new Date(System.currentTimeMillis() + 3600000));  // Added an hour's worth of milliseconds to the current time.
                        urlRequest.setResponseHeaders(override);
                        URL url = s3Client.generatePresignedUrl( urlRequest );*/
                //LocalLog.d(" S3 SERVER IMAGE PATH = ",""+url.toString());
                val filePathOnServer =
                    "${DataLocal.BUCKET_API}$fileNameToUpload"
                Log.d("S3 SERVER IMAGE PATH = ", "" + filePathOnServer)
                filePathOnServer
            } catch (e: Exception) {
                Log.d("Error: ", e.toString())
                ""
            }
        } else {
            ""
        }
    }

    @ExperimentalComposeUiApi
    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        FatherOfAppsTheme {

        }
    }



}