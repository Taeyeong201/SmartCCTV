package com.threetip.smartcctv.controller.litener

interface DetectViewListener {
    fun loadingFinish()
    fun error(msg:String?)
}
