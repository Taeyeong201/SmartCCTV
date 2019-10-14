package com.threetip.smartcctv.subframe

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import com.threetip.smartcctv.R
import com.threetip.smartcctv.adapter.DetectGridAdapter
import com.threetip.smartcctv.controller.LoadImage

@SuppressLint("ValidFragment")
class MainFrame
constructor(private val progressDialog : AlertDialog): Fragment() {
    var _context : Context? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this._context = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.detect_main_frame, container, false)
        val ivFrame = rootView.findViewById<ImageView>(R.id.iv_frame)
        val gvList = rootView.findViewById<GridView>(R.id.gv_image_list)
        gvList.adapter = DetectGridAdapter(context!!)
//        val databaseImage = LoadImage("https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_0%282019-10-09%2001%3A18%3A44%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=Kzl54xuulix5v1mUrkxYKn27%2BpY%3D&Expires=1872951527",
//                progressDialog, ivFrame)
//        databaseImage.execute()

        return rootView
    }
}
