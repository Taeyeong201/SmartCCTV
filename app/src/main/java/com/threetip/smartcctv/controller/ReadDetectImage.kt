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
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class ReadDetectImage constructor(val url: String, val context: Context, private val progressDialog: AlertDialog, private val what:Int) : AsyncTask<Void, Int, Void>() {
    private val requestHttpURLConnection
            by lazy { RequestHttpURLConnection() }


    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.show()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        when(what) {
            HandleValue.DETECT_MAIN_FRAME.ordinal-> {
                val jsonData = DetectingView.getMainJSONArray()
//        val jsonData = JSONArray(requestHttpURLConnection.request(url))
                Log.d("DetectingVIew", jsonData.toString())
                for (i in 0 until jsonData.length()) {
                    val obj = jsonData.getJSONObject(i)
                    val sNo = obj.getInt("S_no")
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
                    DetectingView.detectMainImages.add(
                            DetectImage(sNo, rcCdate, rcUrl, cacheFile.absolutePath))
                }
            }
            HandleValue.DETECT_HEATMAP_FRAME.ordinal-> {
                val jsonData = DetectingView.getHeatMapJSONArray()
//        val jsonData = JSONArray(requestHttpURLConnection.request(url))
                Log.d("DetectingVIew", jsonData.toString())
                for (i in 0 until jsonData.length()) {
                    val obj = jsonData.getJSONObject(i)
                    val sNo = obj.getInt("S_no")
                    val rcCdate = obj.getString("RC_Mdate")
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
                    DetectingView.detectHeatMapImages.add(
                            DetectImage(sNo, rcCdate, rcUrl, cacheFile.absolutePath))
                }
            }
            HandleValue.DETECT_CROP_FRAME.ordinal-> {
                val jsonData = DetectingView.getCropJSONArray()
//        val jsonData = JSONArray(requestHttpURLConnection.request(url))
                Log.d("DetectingVIew", jsonData.toString())
                for (i in 0 until jsonData.length()) {
                    val obj = jsonData.getJSONObject(i)
                    val sNo = obj.getInt("S_no")
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
                    DetectingView.detectCropImages.add(
                            DetectImage(sNo, rcCdate, rcUrl, cacheFile.absolutePath))
                }
            }
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