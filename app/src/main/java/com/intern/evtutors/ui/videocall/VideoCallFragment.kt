package com.intern.evtutors.ui.videocall


import io.agora.rtc.Constants
import io.agora.rtc.internal.LastmileProbeConfig
import io.agora.rtc.ScreenCaptureParameters
import io.agora.rtc.mediaio.AgoraDefaultSource
import kotlinx.coroutines.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.intern.evtutors.R
import com.intern.evtutors.base.fragment.BaseFragment
import com.intern.evtutors.data.models.Lesson
import com.intern.evtutors.databinding.FragmentVideocallBinding
import dagger.hilt.android.AndroidEntryPoint
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class VideoCallFragment:BaseFragment() {
    private val viewModel by viewModels<VideoCallVIewModel>()
    private lateinit var dataBinding:FragmentVideocallBinding
    private lateinit var lesson: Lesson
    private var isCamera:Boolean=true
    var isMicro:Boolean=true
    var isshare:Boolean=true
    private var token:String=""
    private var mRtcEngine: RtcEngine?=null
    private var numberAttendants:Int=1
    private var totalDuration:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args by navArgs<VideoCallFragmentArgs>()
        lesson = args.lesson
        viewModel.fetchData(lesson.channelName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentVideocallBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        val scope = CoroutineScope(Dispatchers.Main + Job())
        //Need to handle exception
        initializeAgoraEngine()
        scope.launch {
            delay(3000)
            if(mRtcEngine!=null) {
                mRtcEngine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
                mRtcEngine!!.setClientRole(1)
                mRtcEngine!!.enableVideo()
                mRtcEngine!!.enableAudio()
                setupLocalVideo()
                joinChannel()
                //checking status
                handleMicroOnOff()
                handleCameraOnOff()
                dataBinding.progressBarLayout.isVisible = false
            } else {
                // show dialog: too long to wait creating mRtcEngine
            }
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.camera.setOnClickListener(View.OnClickListener { view ->
            isCamera=!isCamera
            handleCameraOnOff()
        });
        dataBinding.mic.setOnClickListener(View.OnClickListener { view ->
            isMicro=!isMicro
            handleMicroOnOff()
        });

        dataBinding.call.setOnClickListener {
            handleFinish()
        }
        dataBinding.share.setOnClickListener(View.OnClickListener { View->
            evenShare()
            isshare=!isshare
        })
    }

    private fun initializeAgoraEngine() {
        try {
            viewModel.appInfo.observe(viewLifecycleOwner) {
                mRtcEngine = RtcEngine.create(context, it.appID, mRtcEngineHandler)
            }
        } catch (e:Exception) {
            Log.d("Creating RTC Engine error: ", e.message.toString())
        }
    }

    private fun joinChannel() {
        viewModel.token.observe(viewLifecycleOwner) {
            Log.d("engine: ", lesson.channelName)
            mRtcEngine!!.joinChannel(it.token, lesson.channelName, null, 0)
        }
    }

    //Handle every events in streaming
    private val mRtcEngineHandler:IRtcEngineEventHandler = object:IRtcEngineEventHandler() {
        override fun onUserJoined(uid: Int, elapsed: Int) {
            activity!!.runOnUiThread { setupRemoteVideo(uid) }

        }

        override fun onLeaveChannel(stats: RtcStats?) {
//          We can handle event that show the form for rating the meeting here
            activity!!.runOnUiThread { onRemoteUserLeft() }
        }
        override fun onUserOffline(uid: Int, reason: Int) {
            activity!!.runOnUiThread { onRemoteUserLeft() }
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            activity!!.runOnUiThread { handleLessonStarted() }
        }

        override fun onRtcStats(stats: RtcStats) {
            activity!!.runOnUiThread { handleLessonStatistic(stats) }
        }
    }

    private fun setupRemoteVideo(uid:Int) {
        if(dataBinding.remoteVideoViewContainer.childCount >=1) {
            return
        }
        val surfaceView = RtcEngine.CreateRendererView(context)
        dataBinding.remoteVideoViewContainer.addView(surfaceView)
        mRtcEngine!!.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid))
    }

    private fun setupLocalVideo() {
        var surfaceView = RtcEngine.CreateRendererView(context)
        surfaceView.setZOrderMediaOverlay(true)
        dataBinding.localVideoViewContainer.addView(surfaceView)
        mRtcEngine!!.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0))
    }

    private fun handleLessonStarted() {
//        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
//        coroutineScope.launch {
//            val lessonUpdated = lessonRepository.getLessonById(lesson!!.id)
//            Log.d("lesson id", lessonUpdated.id.toString()  )
//            if(lessonUpdated.status != "1") {
//                lessonUpdated.status = "1"
//                lessonRepository.updateLesson(lessonUpdated)
//            }
//        }
    }

    private fun onRemoteUserLeft() {
//        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
//        coroutineScope.launch {
//            val lessonUpdated = lessonRepository.getLessonById(lesson!!.id)
//            val date = Date()
//            if(numberAttendants<=1) {
//                Log.d("lesson attendant", numberAttendants.toString())
//                if(lessonUpdated.realTimeStart == "0000-00-00 00:00:00") {
//                    lessonUpdated.realTimeStart = getRealTimeStart(totalDuration, date)
//                }
//                lessonUpdated.status = "2"
//                lessonUpdated.realTimeEnd = formatDateTime(date)
//                Log.d("lesson", lessonUpdated.toString())
//            }
//            lessonRepository.updateLesson(lessonUpdated)
//        }
    }

    private suspend fun checkingChannelStatus() {
        //considering:
        if(lesson!!.status == "0") {
//            lessonRepository.updateLesson(lesson!!)
        }
    }


    private fun getRealTimeStart(totalDuration:Int, date:Date):String {
        val result = Date(date.time - (totalDuration*1000))
        return formatDateTime(result)
    }

    private fun formatDateTime(date:Date):String {
        val dateFormat:DateFormat = SimpleDateFormat("yyyy-M-dd hh:mm:ss")
        return dateFormat.format(date)

    }

    private fun handleLessonStatistic(stats: IRtcEngineEventHandler.RtcStats) {
        numberAttendants = stats.users
        totalDuration = stats.totalDuration
    }


    private fun handleCameraOnOff() {
        if(isCamera) {
            dataBinding.camera.setImageResource(R.drawable.ic_camera)
            setupLocalVideo()
            mRtcEngine!!.enableLocalVideo(true)
        } else {
            dataBinding.camera.setImageResource(R.drawable.ic_stopcamera)
            dataBinding.localVideoViewContainer.removeAllViews()
            mRtcEngine!!.enableLocalVideo(false)
        }
    }

    private fun handleMicroOnOff() {
        if(isMicro) {
            dataBinding.mic.setImageResource(R.drawable.ic_mic)
            mRtcEngine!!.enableLocalAudio(true)
        } else {
            dataBinding.mic.setImageResource(R.drawable.ic_mute)
            mRtcEngine!!.enableLocalAudio(false)
        }
    }
    private fun evenShare(){
        if(isshare){
            dataBinding.share.setImageResource(R.drawable.ic_share_start)
            shareScreen()
        }else{
            dataBinding.share.setImageResource(R.drawable.ic_share)
            stopshareScreen()
        }
    }
    private fun shareScreen(){
        mRtcEngine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        mRtcEngine!!.setClientRole(IRtcEngineEventHandler.ClientRole.CLIENT_ROLE_BROADCASTER)
        val screenCaptureParameters = ScreenCaptureParameters()
        screenCaptureParameters.captureAudio = true
        screenCaptureParameters.captureVideo = true
        Log.d("share", "begin")
        val videoCaptureParameters: ScreenCaptureParameters.VideoCaptureParameters = ScreenCaptureParameters.VideoCaptureParameters()
        screenCaptureParameters.videoCaptureParameters = videoCaptureParameters
        mRtcEngine!!.startScreenCapture(screenCaptureParameters)
    }
    private fun stopshareScreen(){
        mRtcEngine!!.stopScreenCapture()
        mRtcEngine!!.setVideoSource(AgoraDefaultSource())
    }

    private fun handleFinish() {
        //change navigation
    }



}