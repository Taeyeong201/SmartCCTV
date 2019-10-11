package com.threetip.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.live_stream_view.*
import android.webkit.WebViewClient



class LiveStreamView :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_stream_view)

        val ws_setting = wv_live.settings
        ws_setting.javaScriptEnabled = true
        ws_setting.loadWithOverviewMode = true
        ws_setting.supportZoom()
        ws_setting.useWideViewPort = true
        wv_live.webViewClient = WebViewClient()

        wv_live.loadUrl("http://221.155.24.64:8090/?action=stream")

    }
}