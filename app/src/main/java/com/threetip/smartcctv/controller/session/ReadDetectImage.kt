package com.threetip.smartcctv.controller.session

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
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.net.HttpURLConnection
import java.net.URL

class ReadDetectImage constructor(private val url: ArrayList<String>, val context: Context, private val progressDialog: AlertDialog) : AsyncTask<Void, Int, Void>() {
    private val requestHttpURLConnection
            by lazy { RequestHttpURLConnection() }


    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.show()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        try {
            var x = 0
            while (x < 3) {
                when (x) {
                    0 -> {
//                val jsonData = DetectingView.getMainJSONArray()
                        val pageResource = requestHttpURLConnection.request(url[x++])
                                ?: throw IllegalArgumentException("No message")
                        val jsonData = JSONArray(pageResource)
                        Log.d("DetectingView", jsonData.toString())
                        val jsonLength: Int = if (jsonData.length() > 100) {
                            100
                        } else {
                            jsonData.length()
                        }

                        for (i in 0 until jsonLength) {
                            val obj = jsonData.getJSONObject(i)
                            val sNo = obj.getInt("s_no")
                            val rcCdate = obj.getString("rc_cdate")
                            val rcUrl = obj.getString("rc_url")
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
                    1 -> {
//                val jsonData = DetectingView.getHeatMapJSONArray()
                        val pageResource = requestHttpURLConnection.request(url[x++])
                                ?: throw IllegalArgumentException("No message")
                        val jsonData = JSONArray(pageResource)
                        Log.d("DetectingVIew", jsonData.toString())
                        val jsonLength: Int = if (jsonData.length() > 100) {
                            100
                        } else {
                            jsonData.length()
                        }

                        for (i in 0 until jsonLength) {
                            val obj = jsonData.getJSONObject(i)
                            val sNo = obj.getInt("s_no")
                            val rcCdate = obj.getString("rc_mdate")
                            val rcUrl = obj.getString("rc_url")
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
                    2 -> {
//                val jsonData = DetectingView.getCropJSONArray()
                        val pageResource = requestHttpURLConnection.request(url[x++])
                                ?: throw IllegalArgumentException("No message")
                        val jsonData = JSONArray(pageResource)
                        Log.d("DetectingVIew", jsonData.toString())

                        val jsonLength: Int = if (jsonData.length() > 100) {
                            100
                        } else {
                            jsonData.length()
                        }

                        for (i in 0 until jsonLength) {
                            val obj = jsonData.getJSONObject(i)
                            val sNo = obj.getInt("s_no")
                            val rcCdate = obj.getString("rc_cdate")
                            val rcUrl = obj.getString("rc_url")
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
            }
        }
        catch ( e : Exception){
//            Looper.prepare()
//            Toast.makeText(context, "Request Fail Error:${e.message}", Toast.LENGTH_LONG).show()
//            Looper.loop()
            Log.d("ReadDetectImage", "Reading fail err : ${e.message}")
            val toHandler = DetectingView.detectHandler.obtainMessage()
            toHandler.what = HandleValue.ERROR.ordinal
            toHandler.obj = e.message
            DetectingView.detectHandler.sendMessage(toHandler)
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