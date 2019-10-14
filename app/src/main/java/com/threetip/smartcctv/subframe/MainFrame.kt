package com.threetip.smartcctv.subframe

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import com.threetip.smartcctv.DetectingView
import com.threetip.smartcctv.R
import com.threetip.smartcctv.adapter.DetectGridAdapter

class MainFrame : Fragment() {
    private var _context: Context? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this._context = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.detect_main_frame, container, false)
        val ivFrame = rootView.findViewById<ImageView>(R.id.iv_frame)
        val gvList = rootView.findViewById<GridView>(R.id.gv_image_list)
        gvList.adapter = DetectGridAdapter(context!!)

        gvList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            ivFrame.setImageBitmap(BitmapFactory.decodeFile(DetectingView.detectMainImages[position].filePath))
        }

        if (DetectingView.detectMainImages.size != 0)
            ivFrame.setImageBitmap(BitmapFactory.decodeFile(DetectingView.detectMainImages[0].filePath))
//        val databaseImage = LoadImage(url,progressDialog, imageView)
//        databaseImage.execute()

        return rootView
    }

}
