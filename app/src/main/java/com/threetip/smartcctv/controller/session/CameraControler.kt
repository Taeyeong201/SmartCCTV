package com.threetip.smartcctv.controller.session

import android.os.AsyncTask
import android.support.v7.app.AlertDialog
import com.threetip.smartcctv.DetectingView
import com.threetip.smartcctv.dto.HandleValue
import com.threetip.smartcctv.dto.PTZValue
import java.io.InputStream
import java.io.OutputStream
import java.net.*

class CameraControler(private val cameraIP:String, private val progressDialog: AlertDialog): AsyncTask<Void, Int, Void>() {

    private val socketAddress:SocketAddress by lazy {
        InetSocketAddress(cameraIP, 9000)
    }

    private val sock: Socket by lazy {
        Socket()
    }
    lateinit var inputStream : InputStream
    lateinit var outputStream : OutputStream

    private val buf = ByteArray(1)

    private fun connect() {
        sock.connect(socketAddress)
        inputStream = sock.getInputStream()
        outputStream = sock.getOutputStream()
    }

    fun sendPTZValue(value :PTZValue){
       Thread({
           when(value) {
               PTZValue.Up -> {
                   buf[0] = 'u'.toByte()
                   outputStream.write(buf)
               }
               PTZValue.Left -> {
                   buf[0] = 'l'.toByte()
                   outputStream.write(buf)
               }
               PTZValue.Right ->{
                   buf[0] = 'r'.toByte()
                   outputStream.write(buf)
               }
               PTZValue.Down ->{
                   buf[0] = 'd'.toByte()
                   outputStream.write(buf)
               }
               PTZValue.Run -> {
                   buf[0] = 't'.toByte()
                   outputStream.write(buf)
               }
               PTZValue.Stop -> {
                   buf[0] = 'f'.toByte()
                   outputStream.write(buf)
               }
           }

       },"SendingThread") .start()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.show()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        connect()
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)

//        val toHandler = DetectingView.detectHandler.obtainMessage()
//        toHandler.what = HandleValue.CAMERA_CONNECTED.ordinal
//        DetectingView.detectHandler.sendMessage(toHandler)
        progressDialog.dismiss()
    }
}