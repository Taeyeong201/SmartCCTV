package com.threetip.myapplication

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.threetip.myapplication.flagment.DetectedFrame
import com.threetip.myapplication.flagment.HeatMapFrame
import com.threetip.myapplication.flagment.MainFrame
import kotlinx.android.synthetic.main.detect_activity.*

class DetectingView : AppCompatActivity() {
    var mode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detect_activity)

        pager.offscreenPageLimit = 3
        val pagerAdapter = MoviePagerAdapter(supportFragmentManager)

        val progressDialog = setProgressDialog(this, "Loading")

        val mainframe = MainFrame(progressDialog)
        val heatMapFrame = HeatMapFrame()
        val detectedFrame = DetectedFrame()

        pagerAdapter.addItem(mainframe)
        pagerAdapter.addItem(heatMapFrame)
        pagerAdapter.addItem(detectedFrame)

        tab_layout.addTab(tab_layout.newTab().setText("Main"))
        tab_layout.addTab(tab_layout.newTab().setText("HeatMap"))
        tab_layout.addTab(tab_layout.newTab().setText("Detected"))


        pager.adapter = pagerAdapter
        tab_layout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(pager))
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))






//        btn_mode.setOnClickListener {
//            when(mode) {
//                0 -> {
//                    tv_detect_mode.text = "사람 감지"
//                    btn_mode.text = "동선 인식"
//                    val databaseImage = LoadImage("https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_0%282019-10-09%2001%3A18%3A44%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=Kzl54xuulix5v1mUrkxYKn27%2BpY%3D&Expires=1872951527",
//                            progressDialog, iv_mode_pic)
//                    databaseImage.execute()
//                    mode = 1
//                }
//                1 -> {
//                    tv_detect_mode.text = "동선 인식"
//                    btn_mode.text = "사람 감지"
//                    iv_mode_pic.setImageResource(R.drawable.person_detect)
//                    mode = 0
//                }
//            }
//        }
    }

    private fun setProgressDialog(context:Context, message:String):AlertDialog {
        val llPadding = 30
        val ll = LinearLayout(context)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam

        val progressBar = ProgressBar(context)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam

        llParam = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(context)
        tvText.text = message
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20.toFloat()
        tvText.layoutParams = llParam

        ll.addView(progressBar)
        ll.addView(tvText)

        val builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        builder.setView(ll)

        val dialog = builder.create()
        val window = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window?.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window?.attributes = layoutParams
        }
        dialog.setCancelable(false)
        return dialog
    }

    class MoviePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val items = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return items[position]
        }

        override fun getCount(): Int {
            return items.size
        }
        fun addItem(item : Fragment) {
            items.add(item)
        }

    }
}

