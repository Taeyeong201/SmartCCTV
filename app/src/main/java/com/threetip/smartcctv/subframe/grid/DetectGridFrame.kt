package com.threetip.smartcctv.subframe.grid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.threetip.smartcctv.R

class DetectGridFrame : ConstraintLayout {
    private lateinit var tv_Sno: TextView
    private lateinit var tv_RcDate: TextView
    private lateinit var iv_thumbnail: ImageView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val test = inflater.inflate(R.layout.detect_grid_item, this, true)
        tv_Sno = test.findViewById(R.id.tv_s_no)
        tv_RcDate = test.findViewById(R.id.tv_rc_date)
        iv_thumbnail = test.findViewById(R.id.iv_thumbnail)

    }

    fun setIv_thumbnail(path: String?) {
        val bitmap = BitmapFactory.decodeFile(path)
        iv_thumbnail.setImageBitmap(bitmap)
    }

    fun setTv_Sno(sNo: Int) {
        tv_Sno.text = "$sNo"
    }

    fun setTv_RcDate(date: String?) {
        tv_RcDate.text = date
    }
}
