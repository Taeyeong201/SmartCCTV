package com.threetip.smartcctv.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.threetip.smartcctv.DetectingView
import com.threetip.smartcctv.subframe.grid.DetectGridFrame

class DetectGridHeatMapAdapter(val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return DetectingView.detectHeatMapImages.size
    }

    override fun getItem(position: Int): Any? {
        return DetectingView.detectHeatMapImages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = DetectGridFrame(context)
        val item = DetectingView.detectHeatMapImages[position]

        view.setIv_thumbnail(item.filePath)
        view.setTv_RcDate(item.rC_Cdate)
        view.setTv_Sno(item.s_no)


        return view
    }
}