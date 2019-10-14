package com.threetip.smartcctv.controller

import android.os.Handler
import android.os.Message
import android.util.Log
import com.threetip.smartcctv.controller.litener.DetectViewListener
import com.threetip.smartcctv.dto.HandleValue

class DetectViewHandler constructor(private val listener: DetectViewListener) : Handler() {

    var isOnlyFinished :Int=0

    override fun handleMessage(msg: Message) {
        val m: String?
        when (msg.what) {
            HandleValue.LOADING_FINISH.ordinal -> {
                if(isOnlyFinished==2) {
                    listener.loadingFinish()
                }
                else {
                    isOnlyFinished++
                }

            }
            HandleValue.TOUCH_DETECT_IMAGE.ordinal -> {

            }
            else -> {
                Log.d("DetectHandler", "")
            }
        }

        super.handleMessage(msg)
    }
}