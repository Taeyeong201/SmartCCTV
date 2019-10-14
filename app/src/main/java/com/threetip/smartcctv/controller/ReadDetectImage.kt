package com.threetip.smartcctv.controller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AlertDialog
import android.util.Log
import com.threetip.smartcctv.DetectingView
import com.threetip.smartcctv.dto.DetectImage
import com.threetip.smartcctv.dto.HandleValue
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

class ReadDetectImage constructor(val url: String, val context: Context, private val progressDialog: AlertDialog) : AsyncTask<Void, Void, Void>() {
    private val requestHttpURLConnection
            by lazy { RequestHttpURLConnection() }

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.show()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        val jsonData = DetectingView.getTestJSONArray()
//        val jsonData = JSONArray(requestHttpURLConnection.request(url))
        Log.d("DetectingVIew", jsonData.toString())
        for (i in 0 until jsonData.length()) {
            val obj = jsonData.getJSONObject(i)
            val sNo = obj.getInt("S_no")
            val rcNo = obj.getInt("RC_NO")
            val rcCdate = obj.getString("RC_Cdate")
            val rcUrl = obj.getString("RC_url")
            val fileName = "${sNo}_$rcCdate.jpg"

            val cacheDir = context.cacheDir
            val cacheFile = File(cacheDir.absolutePath, fileName)
            if (!cacheFile.exists()) {
                val fos = FileOutputStream(cacheFile.absolutePath)
                Log.d("ReadDetectImage", cacheFile.absolutePath)
                val url = URL(rcUrl)
                val conn = url.openConnection() as HttpURLConnection
                conn.doInput = true
                conn.connect()
                val inputStream = conn.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.close()
            }
            DetectingView.detectImages.add(
                    DetectImage(sNo, rcNo, rcCdate, rcUrl, cacheFile.absolutePath))
        }
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)

        val toHandler = DetectingView.detectHandler.obtainMessage()
        toHandler.what = HandleValue.LOADING_FINISH.ordinal
        DetectingView.detectHandler.sendMessage(toHandler)

        progressDialog.dismiss()
    }


}