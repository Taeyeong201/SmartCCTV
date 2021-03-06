package com.threetip.smartcctv

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.menu_view.*
import android.support.v7.app.AlertDialog
import com.threetip.smartcctv.MainActivity.Companion.CameraIP
import com.threetip.smartcctv.MainActivity.Companion.isCamera
import com.threetip.smartcctv.controller.ProgressBarMaker
import com.threetip.smartcctv.controller.session.CameraControler
import com.threetip.smartcctv.dto.PTZValue


class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_view)

        btn_live.setOnClickListener {
            liveStreamIntent()
        }
        cb_security.setOnClickListener {
            securityMessage()
        }

        btn_graph.setOnClickListener {
            val graphIntent = Intent(this, GraphView::class.java)
            startActivity(graphIntent)
        }

        ibtn_detecting.setOnClickListener {
            val detectViewIntent = Intent(this, DetectingView::class.java)
            startActivity(detectViewIntent)
        }

        val progressBar = ProgressBarMaker(this, "Loading").getProgressDialog()
        if(isCamera) {
            cameraControl = CameraControler(CameraIP, progressBar)
            cameraControl.execute()
        }
    }

    private fun liveStreamIntent() {
        val liveStreamIntent = Intent(this, LiveStreamView::class.java)
        startActivity(liveStreamIntent)
    }

    private fun securityMessage() {
        if (cb_security.isChecked) {
            Toast.makeText(this, "침입 감지 모드 실행 중", Toast.LENGTH_SHORT).show()
            if(isCamera) {
                cameraControl.sendPTZValue(PTZValue.Run)
            }
        } else {
            val alertDialogBuilder = AlertDialog.Builder(this)

            // 제목셋팅
            alertDialogBuilder.setTitle("침입 감지")

            // AlertDialog 셋팅
            alertDialogBuilder
                    .setMessage("침입 감지를 종료할 것입니까?")
                    .setCancelable(false)
                    .setPositiveButton("종료"
                    ) { _, _ ->
                        // 프로그램을 종료한다
                        if(isCamera) {
                            cameraControl.sendPTZValue(PTZValue.Stop)
                        }
                        Toast.makeText(this, "침입 감지 모드 종료", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("취소"
                    ) { dialog, _ ->
                        // 다이얼로그를 취소한다
                        dialog.cancel()
                        cb_security.performClick()
                    }
            // 다이얼로그 생성
            val alertDialog = alertDialogBuilder.create()
            // 다이얼로그 보여주기
            alertDialog.show()

        }
    }

    companion object {
        lateinit var cameraControl:CameraControler
    }
}