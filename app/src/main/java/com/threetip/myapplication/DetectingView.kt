package com.threetip.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.detect_view.*
import kotlinx.android.synthetic.main.menu_view.*

class DetectingView : AppCompatActivity() {
    var mode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detect_view)


        btn_mode.setOnClickListener {
            when(mode) {
                0 -> {
                    tv_detect_mode.text = "사람 감지"
                    btn_mode.text = "동선 인식"
                    iv_mode_pic.setImageResource(R.drawable.moving_detect)
                    mode = 1
                }

                1 -> {
                    tv_detect_mode.text = "동선 인식"
                    btn_mode.text = "사람 감지"
                    iv_mode_pic.setImageResource(R.drawable.person_detect)
                    mode = 0
                }
            }
        }
    }
}