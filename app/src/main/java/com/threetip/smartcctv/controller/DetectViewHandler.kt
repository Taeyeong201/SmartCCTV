package com.threetip.smartcctv.controller

import android.os.Handler
import android.os.Message
import android.util.Log
import com.threetip.smartcctv.controller.litener.DetectViewListener
import com.threetip.smartcctv.dto.HandleValue

class DetectViewHandler constructor(private val listener: DetectViewListener) : Handler() {

    override fun handleMessage(msg: Message) {
        val m: String?
        when (msg.what) {
            HandleValue.LOADING_FINISH.ordinal -> {
                listener.loadingFinish()
            }
            HandleValue.ERROR.ordinal -> {
                listener.error(msg.obj as String?)
            }
            else -> {
                Log.d("DetectHandler", "")
            }
        }

        super.handleMessage(msg)
    }
}