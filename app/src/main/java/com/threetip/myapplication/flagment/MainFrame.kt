package com.threetip.myapplication.flagment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.threetip.myapplication.R
import com.threetip.myapplication.controller.LoadImage
import kotlinx.android.synthetic.main.detect_main_frame.*

@SuppressLint("ValidFragment")
class MainFrame constructor(private val progressDialog : AlertDialog) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val databaseImage = LoadImage("https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_0%282019-10-09%2001%3A18%3A44%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=Kzl54xuulix5v1mUrkxYKn27%2BpY%3D&Expires=1872951527",
                progressDialog, iv_frame)
        databaseImage.execute()

        return inflater.inflate(R.layout.detect_main_frame, container, false)
    }
}
