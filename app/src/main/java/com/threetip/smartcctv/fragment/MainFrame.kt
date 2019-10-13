package com.threetip.smartcctv.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.threetip.smartcctv.R
import com.threetip.smartcctv.controller.LoadImage

@SuppressLint("ValidFragment")
class MainFrame constructor(private val progressDialog : AlertDialog) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.detect_main_frame, container, false)
        val ivFrame = rootView.findViewById<ImageView>(R.id.iv_frame)
        val databaseImage = LoadImage("https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_0%282019-10-09%2001%3A18%3A44%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=Kzl54xuulix5v1mUrkxYKn27%2BpY%3D&Expires=1872951527",
                progressDialog, ivFrame)
        databaseImage.execute()

        return rootView
    }
}
