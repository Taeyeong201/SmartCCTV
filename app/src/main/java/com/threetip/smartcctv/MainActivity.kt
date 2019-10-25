package com.threetip.smartcctv

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.threetip.smartcctv.controller.session.RequestHttpURLConnection
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_signUp.visibility = View.GONE

        btn_signUp.setOnClickListener {
            signUpIntent()
        }

        btn_login.setOnClickListener {
            val text = et_userID.text.toString()

            when {
                text.isEmpty() -> Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show()
                "user1" == text -> {
                    Thread {
                        val cameraData = RequestHttpURLConnection().request("http://192.168.0.24:8080/controller/android/getListCctvIP")
                                ?: "no data"
                        if (cameraData == "no data") {
                            Toast.makeText(this, "Camera 정보 없음", Toast.LENGTH_LONG).show()
                        } else {
                            CameraIP = JSONArray(cameraData).getJSONObject(0).getString("r_ip")
                            Log.d("Main", "ReadCameraIP : $CameraIP")
                            loginIntent()
                        }
                    }.start()
                }
                "user2" == text -> loginIntent()
                else -> AlertDialog.Builder(this)
                        .setTitle("로그인 실패")
                        .setMessage("없는 아이디 입니다.")
                        .setNeutralButton("닫기"

                        ) { dlg, sumthin ->

                        }.show()
            }
        }

//        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
//        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
//            // 권한 없음
//        } else {
//            // 권한 있음
//        }
    }

    private fun signUpIntent() {
        val signUpIntent = Intent(this, SignUpView::class.java)
        startActivity(signUpIntent)
        finish()
    }

    private fun loginIntent() {
        val mainMenuIntent = Intent(this, MainMenu::class.java)
        startActivity(mainMenuIntent)
        finish()
    }

    companion object {
        lateinit var CameraIP: String
    }
}
