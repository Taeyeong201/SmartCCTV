package com.threetip.smartcctv.controller

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AlertDialog
import android.widget.ImageView

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * not using
 * ex. LoadImage(url, progressDialog, imageView)
 */
class LoadImage
(private val imgPath: String, private val progressDialog: AlertDialog, private val imageView : ImageView)
    // 이 코드는 메모리누수이 생길 수 있는 코딩
    // https://dwenn.tistory.com/48 해결방안
    : AsyncTask<Void, Void, Bitmap>() {

    override fun doInBackground(vararg params: Void?): Bitmap? {
        return getBitmap()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.show()
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imageView.setImageBitmap(result)
        progressDialog.dismiss()
    }

    private var bitmap: Bitmap? = null

    private fun getBitmap(): Bitmap? {
        val imgThread = object : Thread() {
            override fun run() {
                try {
                    val url = URL(imgPath)
                    val conn = url.openConnection() as HttpURLConnection
                    conn.doInput = true
                    conn.connect()
                    val inputStream = conn.inputStream
                    bitmap = BitmapFactory.decodeStream(inputStream)
                } catch (ignored: IOException) {
                }
            }
        }
        imgThread.start()
        try {
            imgThread.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } finally {
            return bitmap
        }
    }
}