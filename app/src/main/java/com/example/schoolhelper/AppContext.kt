package com.example.schoolhelper

import android.app.Application
import android.nfc.Tag
import android.util.Log
import com.tencent.mmkv.MMKV

class AppContext: Application() {
    override fun onCreate() {
        super.onCreate()
        instance=this
        initMMKV()
    }
    private fun initMMKV()
    {
        val rootDir=MMKV.initialize(this)
        Log.d(TAG, "initMMKV: $rootDir")

    }

    fun logout() {
        TODO("Not yet implemented")
    }

    fun onLogin() {

    }

    companion object{
        lateinit var instance: AppContext
        const val TAG="AppContext"
    }
}