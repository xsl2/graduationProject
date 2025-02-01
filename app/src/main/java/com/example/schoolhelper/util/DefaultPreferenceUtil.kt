package com.example.schoolhelper.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

//设置偏好工具类
/*
* 系统默认偏好工具，本项目中只保存一个值用来判断是否同意相关协议，TERMS_SERVICE
* */
//DefaultPreferenceUtil 使用了 单例模式（Singleton）来确保类的实例只有一个。

class DefaultPreferenceUtil(context: Context) {

    private  var context: Context = context.applicationContext
    //context.applicationContext 用来确保使用应用级的 Context，以避免内存泄漏（避免持有 Activity 或 Service 的引用）。
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
        //getInstance() 方法是线程安全的，使用了 @Synchronized 注解确保在多线程环境下只有一个实例被创建。
        @Synchronized
        fun getInstance(context:Context):DefaultPreferenceUtil{
            if(instance==null)
                instance=DefaultPreferenceUtil(context)
            return instance!!
        }
    }

}