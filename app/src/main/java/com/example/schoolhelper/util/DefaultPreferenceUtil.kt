package com.example.schoolhelper.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

//设置偏好工具类
/*
* 系统默认骗好工具，本项目中只保存一个值用来判断是否同意相关协议，TERMS_SERVICE
* */
class DefaultPreferenceUtil(context: Context) {
    private  var context: Context = context.applicationContext
    private val preference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
    fun setAcceptTermsServiceAgreement()
    {
        putBoolean(TERMS_SERVICE,true)
    }

    val isAcceptTermsServiceAgreement: Boolean
        get() = preference.getBoolean(TERMS_SERVICE, false)

    private fun putBoolean(key: String, value: Boolean) {
        preference.edit().putBoolean(key, value).apply()
    }

    companion object{
        private var instance:DefaultPreferenceUtil?=null
        private const val NAME = "xsl_helper"
        private const val TERMS_SERVICE = "TERMS_SERVICE"
        @Synchronized
        fun getInstance(context:Context):DefaultPreferenceUtil{
            if(instance==null)
                instance=DefaultPreferenceUtil(context)
            return instance!!
        }
    }

}