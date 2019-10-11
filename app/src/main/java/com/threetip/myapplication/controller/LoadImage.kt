package com.threetip.myapplication.controller

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class LoadImage(private val imgPath: String, private val progressDialog: AlertDialog, val imageView : ImageView) : AsyncTask<Void, Void, Bitmap>() {
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