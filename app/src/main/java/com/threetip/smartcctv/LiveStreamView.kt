package com.threetip.smartcctv

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.live_stream_view.*
import android.webkit.WebViewClient
import com.threetip.smartcctv.MainMenu.Companion.cameraControl
import com.threetip.smartcctv.controller.ProgressBarMaker
import com.threetip.smartcctv.dto.PTZValue


class LiveStreamView :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_stream_view)

        val wsSetting = wv_live.settings
        wsSetting.javaScriptEnabled = true
        wsSetting.loadWithOverviewMode = true
        wsSetting.supportZoom()
        wsSetting.useWideViewPort = true
        wv_live.webViewClient = WebViewClient()

        wv_live.loadUrl("http://221.155.24.64:8090/?action=stream")

        val progressBar = ProgressBarMaker(this, "loading").getProgressDialog()

//        val cameraControl = CameraConnector(MainActivity.CameraIP, progressBar)

        ibtn_down.setOnClickListener {
            cameraControl.sendPTZValue(PTZValue.Down)
        }
        ibtn_left.setOnClickListener {
            cameraControl.sendPTZValue(PTZValue.Left)
        }
        ibtn_right.setOnClickListener {
            cameraControl.sendPTZValue(PTZValue.Right)
        }
        ibtn_up.setOnClickListener {
            cameraControl.sendPTZValue(PTZValue.Up)
        }
    }
}