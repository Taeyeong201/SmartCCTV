package com.threetip.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.graph_view.*
import kotlinx.android.synthetic.main.live_stream_view.*
import kotlinx.android.synthetic.main.live_stream_view.wv_live

class GraphView : AppCompatActivity(){
    var what = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.graph_view)

        val ws_setting = wv_live.settings
        ws_setting.javaScriptEnabled = true
        ws_setting.loadWithOverviewMode = true
        ws_setting.useWideViewPort = true
        wv_live.webViewClient = WebViewClient()
        ws_setting.builtInZoomControls = true
        ws_setting.setSupportZoom(true)
        ws_setting.cacheMode = WebSettings.LOAD_NO_CACHE

        wv_live.loadUrl("http://221.155.250.34:8082/controller/Customer/chartand?data=month")



        btn_graph_day.setOnClickListener {
            what = if(what) {
                wv_live.loadUrl("http://221.155.250.34:8082/controller/Customer/chartand?data=day")
                btn_graph_day.text = "month"
                false
            } else {
                wv_live.loadUrl("http://221.155.250.34:8082/controller/Customer/chartand?data=month")
                btn_graph_day.text = "day"
                true
            }
        }
    }
}