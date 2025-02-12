package com.example.schoolhelper

import android.app.Application
import android.nfc.Tag
import android.util.Log
import com.drake.channel.sendEvent
import com.example.schoolhelper.component.login.LoginStatusChangedEvent
import com.example.schoolhelper.util.PreferenceUtil
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
        logoutSilence()
    }

    private fun logoutSilence() {
        //清除登录相关信息
        PreferenceUtil.logout()
        loginStatusChanged()
    }

    fun onLogin() {
        loginStatusChanged()
    }

    private fun loginStatusChanged() {
        sendEvent(LoginStatusChangedEvent())
    }

    companion object{
        lateinit var instance: AppContext
        const val TAG="AppContext"
    }
}