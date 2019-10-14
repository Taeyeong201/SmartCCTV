package com.threetip.smartcctv

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.threetip.smartcctv.adapter.DetectGridAdapter
import com.threetip.smartcctv.controller.ProgressBarMaker
import com.threetip.smartcctv.subframe.DetectedFrame
import com.threetip.smartcctv.adapter.FlagPageAdapter
import com.threetip.smartcctv.controller.DetectViewHandler
import com.threetip.smartcctv.controller.ReadDetectImage
import com.threetip.smartcctv.controller.litener.DetectViewListener
import com.threetip.smartcctv.dto.DetectImage
import com.threetip.smartcctv.subframe.HeatMapFrame
import com.threetip.smartcctv.subframe.MainFrame
import kotlinx.android.synthetic.main.detect_activity.*
import kotlinx.android.synthetic.main.detect_main_frame.*
import org.json.JSONArray
import java.io.File


class DetectingView : AppCompatActivity() {
    var mode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detect_activity)
        clearApplicationData(this)
        pager.offscreenPageLimit = 3
        val pagerAdapter = FlagPageAdapter(supportFragmentManager)

        val progressBarMaker = ProgressBarMaker(this, "Loading")
        val progressDialog = progressBarMaker.getProgressDialog()
        val progressDialog1 = progressBarMaker.getProgressDialog()

        detectHandler = DetectViewHandler(object : DetectViewListener {
            override fun loadingFinish() {
                pagerAdapter.notifyDataSetChanged()
            }
        })

        ReadDetectImage("test", this, progressDialog).execute()

        //어뎁터에 Fragment 추가
        pagerAdapter.addItem(MainFrame(progressDialog1))
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


    // java static
    companion object {
        val detectImages = ArrayList<DetectImage>()
        lateinit var detectHandler:DetectViewHandler
        fun getTestJSONArray(): JSONArray {
            return JSONArray("[\n" +
                    " {\n" +
                    "   \"S_no\": 16434,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:18:44\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_0%282019-10-09%2001%3A18%3A44%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=Kzl54xuulix5v1mUrkxYKn27%2BpY%3D&Expires=1872951527\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16435,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:18:49\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_1%282019-10-09%2001%3A18%3A49%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=%2FEgIxag5YivrjZiFDZDjDJQwkg4%3D&Expires=1872951530\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16436,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:18:52\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_2%282019-10-09%2001%3A18%3A52%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=DiaDofZjLj1fiHPgoHDFOCnfpAY%3D&Expires=1872951533\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16437,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:18:55\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_3%282019-10-09%2001%3A18%3A55%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=SQjkNo54tlYMAU7paVmZ2Nx8W1Q%3D&Expires=1872951537\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16438,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:18:59\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_4%282019-10-09%2001%3A18%3A59%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=MKmLHTcU05pjGPN2elYCGBEC%2FL8%3D&Expires=1872951540\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16439,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:02\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_5%282019-10-09%2001%3A19%3A02%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=WWlawZhr4zVQTg3o9EjipFCGjQk%3D&Expires=1872951544\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16440,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:05\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_6%282019-10-09%2001%3A19%3A05%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=zgH1QVgGQLhy%2FqZOh0rmRnI14Xc%3D&Expires=1872951547\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16441,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:08\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_7%282019-10-09%2001%3A19%3A08%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=yiS2GWpfmVSsMK6giSOdVD44jvg%3D&Expires=1872951550\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16442,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:12\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_8%282019-10-09%2001%3A19%3A12%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=eT6y4uHbwnLU10wsDTwP85%2B29l8%3D&Expires=1872951553\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16443,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:15\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_9%282019-10-09%2001%3A19%3A15%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=xZGdbGbwr3yNhjWDoP2ABY2af88%3D&Expires=1872951557\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16444,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:18\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_10%282019-10-09%2001%3A19%3A18%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=Y5EYLHnC7KJ%2FjDxsItUe0dwc04o%3D&Expires=1872951560\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16445,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:22\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_11%282019-10-09%2001%3A19%3A22%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=wMSwzWL9Gd3RL8DeLE77B8mMrwA%3D&Expires=1872951563\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16446,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:25\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_12%282019-10-09%2001%3A19%3A25%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=B0qI8Ms8q%2FclhiIAtXcNRHDCKpA%3D&Expires=1872951567\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16447,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:29\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_13%282019-10-09%2001%3A19%3A29%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=%2Bx%2FkRzcUJG0zIqnF4ajBA1194TQ%3D&Expires=1872951571\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16448,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:33\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_14%282019-10-09%2001%3A19%3A33%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=aWjdahbiSay4X5g67zcEXlHfvUw%3D&Expires=1872951574\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16449,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:36\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_15%282019-10-09%2001%3A19%3A36%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=Rj%2BEiixArGcOXD5ef8Ke4tuL%2Bfw%3D&Expires=1872951578\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16450,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:40\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_16%282019-10-09%2001%3A19%3A40%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=dTavEpvb8JTYqhwO01IDpfUk9u4%3D&Expires=1872951582\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16451,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:44\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_17%282019-10-09%2001%3A19%3A44%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=XBR2pZr%2B6DU%2BKhmhcCEWRFkvgm0%3D&Expires=1872951586\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16452,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:49\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_18%282019-10-09%2001%3A19%3A49%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=2BRQI82NZ2WA04mLiWzHM91DScg%3D&Expires=1872951590\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16453,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:53\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_19%282019-10-09%2001%3A19%3A53%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=yYje6gz6zaX4jMEkTencxc9kHLY%3D&Expires=1872951594\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16454,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:19:56\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_20%282019-10-09%2001%3A19%3A56%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=1Gldu7fqSwJDj3mav37rd3iyoiY%3D&Expires=1872951598\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16455,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:00\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_21%282019-10-09%2001%3A20%3A00%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=FK%2Bidxu0%2Baylfum4xV5xZrZX5mw%3D&Expires=1872951601\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16456,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:04\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_22%282019-10-09%2001%3A20%3A04%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=rTlJmK%2BDhMWzNjhTrPEEYxk3qUc%3D&Expires=1872951606\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16457,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:08\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_23%282019-10-09%2001%3A20%3A08%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=hlVAOvaNnRxKlgsUQe0Bj4Djvhw%3D&Expires=1872951610\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16458,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:12\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_24%282019-10-09%2001%3A20%3A12%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=qcIqGCYq7HefkrR2R1%2FR2r0Fmys%3D&Expires=1872951614\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16459,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:16\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_25%282019-10-09%2001%3A20%3A16%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=v33oGhpVYgXRYffrmtZEsZ5l0aU%3D&Expires=1872951618\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16460,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:20\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_26%282019-10-09%2001%3A20%3A20%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=kBzvE3U4rB7p5Es1%2FoWFf0U2clg%3D&Expires=1872951622\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16461,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:24\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_27%282019-10-09%2001%3A20%3A24%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=59S6s7aZpvtWI5%2BQsS0JmoZQL0M%3D&Expires=1872951625\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16462,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:27\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_28%282019-10-09%2001%3A20%3A27%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=H8gag1FTOkioFyj6n4nX7Vfczck%3D&Expires=1872951629\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16463,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:30\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_29%282019-10-09%2001%3A20%3A30%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=zPLQOv2sLYiNqSoAqOvwJ1OUE8M%3D&Expires=1872951632\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16464,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:34\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_30%282019-10-09%2001%3A20%3A34%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=bhci3E3fU92K%2BLoU17zfHOGHSYM%3D&Expires=1872951635\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16465,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:37\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_31%282019-10-09%2001%3A20%3A37%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=qu%2BVWRz0jzsTLyQo8lksy5%2BF%2FBE%3D&Expires=1872951639\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16466,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:40\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_32%282019-10-09%2001%3A20%3A40%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=3sNyFrXfTlafsuFFCwCgifY3oFU%3D&Expires=1872951642\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16467,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:44\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_33%282019-10-09%2001%3A20%3A44%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=zXVxBngxyIU80yWaBFZrx1RO75Y%3D&Expires=1872951645\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16468,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:47\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_34%282019-10-09%2001%3A20%3A47%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=9hqCMyBueAl%2F0N%2F8wzFH670nhzc%3D&Expires=1872951648\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16469,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:50\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_35%282019-10-09%2001%3A20%3A50%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=0d4RqPou%2Bmm6Bl0TdCxdm7SvMvY%3D&Expires=1872951651\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16470,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:53\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_36%282019-10-09%2001%3A20%3A53%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=GISKLaxSCX%2FXM3FCAhDTQSNG%2Bbg%3D&Expires=1872951655\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16471,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:20:57\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_37%282019-10-09%2001%3A20%3A57%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=cNVcNlyaGpVYQCg2Rlxws9eHCd4%3D&Expires=1872951659\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16472,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:02\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_38%282019-10-09%2001%3A21%3A02%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=WxjASdUCCyEgHrOQZ8Mp80hPlSQ%3D&Expires=1872951663\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16473,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:05\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_39%282019-10-09%2001%3A21%3A05%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=2XjEv7u4Roc7cV1Ts2%2FHUcN%2BA4I%3D&Expires=1872951667\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16474,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:10\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_40%282019-10-09%2001%3A21%3A10%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=TZVwFcu%2FA9GKk61R7yI%2FfC5wwXY%3D&Expires=1872951672\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16475,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:15\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_41%282019-10-09%2001%3A21%3A15%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=%2FkLv%2BsnNXLPyDg0FUvtcc%2BryyQ0%3D&Expires=1872951678\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16476,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:21\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_42%282019-10-09%2001%3A21%3A21%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=nghb0PwiIDcMTh8C92481xiGi4I%3D&Expires=1872951683\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16477,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:26\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_43%282019-10-09%2001%3A21%3A26%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=GWejL3dnFdAMgBekGLaE%2FSZGJ6k%3D&Expires=1872951688\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16478,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:30\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_44%282019-10-09%2001%3A21%3A30%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=fH5ACbeo77Dxosz1Uc88fbrciSI%3D&Expires=1872951692\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16479,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:35\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_45%282019-10-09%2001%3A21%3A35%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=JAglvvKnmfPsVU46lKNrl732qfw%3D&Expires=1872951697\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16480,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:39\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_46%282019-10-09%2001%3A21%3A39%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=QDWYhG%2B7hWZuyt%2FBIsYUSJc9pQo%3D&Expires=1872951701\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16481,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:44\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_47%282019-10-09%2001%3A21%3A44%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=ZH4mz8pnomC3H6jfWpqAZ%2Fk%2F3iw%3D&Expires=1872951706\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16482,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:48\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_48%282019-10-09%2001%3A21%3A48%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=XMqS3lfNXno11ofW6K65NjZ2lSo%3D&Expires=1872951710\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16483,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:52\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_49%282019-10-09%2001%3A21%3A52%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=sn0FN0koz4OTrh3MBeiRgzCSAws%3D&Expires=1872951714\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16484,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:21:56\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_50%282019-10-09%2001%3A21%3A56%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=5O%2FAy7EgtNKx0F%2BlXfafov%2FpUEc%3D&Expires=1872951719\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16485,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:22:01\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_51%282019-10-09%2001%3A22%3A01%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=OIOjKgNS2c%2B7V0BA2bCQ70Yyf7w%3D&Expires=1872951723\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16486,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:22:06\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_52%282019-10-09%2001%3A22%3A06%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=vpKIlpSTni5XOAjM46pSGBscgXA%3D&Expires=1872951727\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16487,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:22:09\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_53%282019-10-09%2001%3A22%3A09%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=zwbDeNsFH%2B6MHCjOuwS2EWYUTRo%3D&Expires=1872951731\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16488,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:22:13\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_54%282019-10-09%2001%3A22%3A13%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=yq0uGuvhzvP0%2F4rrhx5ZBFImpkg%3D&Expires=1872951735\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16489,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:22:17\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_55%282019-10-09%2001%3A22%3A17%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=3tGIRizjB%2Fh%2BdWD8H2y8a5KQ7Ok%3D&Expires=1872951738\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16490,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:22:20\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_56%282019-10-09%2001%3A22%3A20%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=kW26R%2F4AbDcL%2FL5vquHKu6Lg5ks%3D&Expires=1872951742\"\n" +
                    " },\n" +
                    " {\n" +
                    "   \"S_no\": 16491,\n" +
                    "   \"RC_NO\": 1,\n" +
                    "   \"RC_Cdate\": \"2019-10-09 01:22:24\",\n" +
                    "   \"RC_url\": \"https://smartcctvsystems.s3.amazonaws.com/vtest.avi/frame/frame_57%282019-10-09%2001%3A22%3A24%29.jpg?AWSAccessKeyId=AKIAJRMH7FS7TDMNHKHA&Signature=aWCT0ELaZ073g3EMTFcAuRdgcO8%3D&Expires=1872951746\"\n" +
                    " }\n" +
                    "]")
        }
    }

    fun clearApplicationData(context: Context) {
        val cache = context.cacheDir
        val appDir = File(cache.parent)
        if (appDir.exists()) {
            val children = appDir.list()
            for (s in children) {
                //다운로드 파일은 지우지 않도록 설정
                if(s == "lib" || s == "files") continue;
                deleteDir(File(appDir, s))
                Log.d("test", "File /data/data/" + context.packageName + "/" + s + " DELETED")
            }
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir!!.delete()
    }
}

