package com.threetip.smartcctv.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.threetip.smartcctv.DetectingView.Companion.detectMainImages
import com.threetip.smartcctv.subframe.grid.DetectGridFrame

class DetectGridAdapter(val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return detectMainImages.size
    }

    override fun getItem(position: Int): Any? {
        return detectMainImages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = DetectGridFrame(context)
        val item = detectMainImages[position]

        view.setIv_thumbnail(item.filePath)
        view.setTv_RcDate(item.rC_Cdate)
        view.setTv_Sno(item.s_no)

//        val tv_Sno: TextView
//        val tv_RcDate: TextView
//        val iv_thumbnail: ImageView
//        var test: LayoutInflater
//        var test1: View
//        if (convertView == null) {
//            test = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            test1 = test.inflate(R.layout.detect_grid_item, null, false)
//            tv_Sno = test1.findViewById<TextView>(R.id.tv_s_no)
//            tv_RcDate = test1.findViewById<TextView>(R.id.tv_rc_date)
//            iv_thumbnail = test1.findViewById<ImageView>(R.id.iv_thumbnail)
//        } else {
//            tv_Sno = convertView.findViewById<TextView>(R.id.tv_s_no)
//            tv_RcDate = convertView.findViewById<TextView>(R.id.tv_rc_date)
//            iv_thumbnail = convertView.findViewById<ImageView>(R.id.iv_thumbnail)
//        }
//
//
//        tv_Sno.text = "${item.s_no}"
//        tv_RcDate.text = item.rC_Cdate
//        val bitmap = BitmapFactory.decodeFile(item.filePath)
//        iv_thumbnail.setImageBitmap(bitmap)

        return view
    }
}
