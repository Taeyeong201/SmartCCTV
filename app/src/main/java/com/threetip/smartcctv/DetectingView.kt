package com.threetip.smartcctv

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.threetip.smartcctv.controller.ProgressBarMaker
import com.threetip.smartcctv.fragment.DetectedFrame
import com.threetip.smartcctv.adapter.FlagPageAdapter
import com.threetip.smartcctv.fragment.HeatMapFrame
import com.threetip.smartcctv.fragment.MainFrame
import kotlinx.android.synthetic.main.detect_activity.*

class DetectingView : AppCompatActivity() {
    var mode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detect_activity)

        pager.offscreenPageLimit = 3
        val pagerAdapter = FlagPageAdapter(supportFragmentManager)

        val progressBarMaker = ProgressBarMaker(this, "Loading")
        val progressDialog = progressBarMaker.getProgressDialog()

        //어뎁터에 Fragment 추가
        pagerAdapter.addItem(MainFrame(progressDialog))
        pagerAdapter.addItem(HeatMapFrame())
        pagerAdapter.addItem(DetectedFrame())

        tab_layout.addTab(tab_layout.newTab().setText("Main"))
        tab_layout.addTab(tab_layout.newTab().setText("HeatMap"))
        tab_layout.addTab(tab_layout.newTab().setText("Detected"))


        pager.adapter = pagerAdapter

        //Tab 과 ViewPager 연동
        tab_layout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(pager))
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

    }
}

