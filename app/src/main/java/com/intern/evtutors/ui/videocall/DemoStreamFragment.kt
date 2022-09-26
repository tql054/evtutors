package com.intern.evtutors.ui.videocall

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.intern.evtutors.R
import com.intern.evtutors.activities.MainActivity
import com.intern.evtutors.base.fragment.BaseFragment
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.databinding.FragmentDemoStreamBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoStreamFragment:BaseFragment() {
    //state of camera and micro
    private var cameraStatus:Boolean = true
    private var microStatus:Boolean = true
    private var lesson:Lesson? = null

    private lateinit var dataBinding:FragmentDemoStreamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args by navArgs<DemoStreamFragmentArgs>()
        lesson = args.lesson
        dataBinding = FragmentDemoStreamBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        if(!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO), 22)
        }
        startCamera()
        dataBinding.btnCamera.setOnClickListener {
            cameraStatus=!cameraStatus
            handleOnclickCamera()
        }

        dataBinding.btnMicro.setOnClickListener {
            microStatus=!microStatus
            handleOnclickMicro()
        }

        //Navigation -> homeFragment
        dataBinding.btnCancel.setOnClickListener {
//            var intent: Intent = Intent(, MainActivity::class.java)
//            startActivity(intent)
        }

        dataBinding.btnStart.setOnClickListener {
            if(lesson!=null) {
                Log.d("lesson as: ", lesson!!.channelName)
                val bundle = bundleOf("lesson" to lesson)
                dataBinding.btnCancel.isEnabled = false
                val action = DemoStreamFragmentDirections.actionDemoStreamFragmentToVideoCallFragment(lesson!!)
                findNavController().navigate(action)
            }
        }
        return dataBinding.root
    }

    private fun handleOnclickCamera() {
        dataBinding.previewView.isVisible = cameraStatus
        dataBinding.txtCamera.isVisible = !cameraStatus
        if(cameraStatus) {
            dataBinding.btnCamera.setImageResource(R.drawable.ic_camera2)
        } else {
            dataBinding.btnCamera.setImageResource(R.drawable.ic_uncapture)
        }
    }

    private fun handleOnclickMicro() {
        if(microStatus) {
            dataBinding.btnMicro.setImageResource(R.drawable.ic_micro)
        } else {
            dataBinding.btnMicro.setImageResource(R.drawable.ic_unvoice)
        }
    }


    //    handle start camera
    private fun startCamera() {
        // Sử dụng để ràng buộc vòng đời của máy ảnh với vòng đời của View. Điều này giúp loại bỏ nhiệm vụ mở và đóng máy ảnh vì CameraX nhận biết được vòng đời.
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context!!)

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(dataBinding.previewView.surfaceProvider)
                }

            // Lựa chọn mặc định dùng camera trước.
            val cameraSelector: CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            // Bên trong khối try, hãy đảm bảo không có gì liên kết với cameraProvider, sau đó liên kết cameraSelector và đối tượng Preview với cameraProvider.
            try {
                // Huỷ liên kết với vòng đời của View trước khi liên kết trở lại
                cameraProvider.unbindAll()

                // Liên kết Preview use case đến Camera.
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview)

            } catch(exc: Exception) {
                Log.e("camera preview", "Liên kết thất bại", exc)
            }

        }, ContextCompat.getMainExecutor(context!!)) //Chạy trên luồng chính.
    }


    //    checking all camera permissions
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            context!!, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    //COMPANION OBJECT: consist of methods that we want to use without creating 'class'
    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)
    }
}