package com.threetip.myapplication

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.WRITE_CALENDAR
import android.content.DialogInterface
import android.opengl.Visibility
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.menu_view.*


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
                "user1" == text -> loginIntent()
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
}
