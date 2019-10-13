package com.threetip.smartcctv

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.live_stream_view.*
import android.webkit.WebViewClient



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

    }
}