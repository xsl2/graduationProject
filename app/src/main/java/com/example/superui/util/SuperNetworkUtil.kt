package com.example.superui.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * 网络工具类
 *
 *
 * 可以判断是否有网络链接，以及是WiFi，还是移动网络
 */
object SuperNetworkUtil {
    /**
     * 网络是否连接了
     *
     * @param context
     * @return
     */
    fun isNetworkConnected(context: Context): Boolean {
        if (context != null) {
            val connectivityManager = getConnectivityManager(context)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo != null) {
                    return networkInfo.isAvailable
                }
            } else {
                //获取当前激活的网络
                val activeNetwork = connectivityManager.activeNetwork ?: return false

                //获取激活的网络详细信息
                val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
                    ?: return false
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    //可以正常上网
                    return true
                }
            }
        }
        return false
    }

    /**
     * WiFi网络是否连接了
     *
     * @param context
     * @return
     */
    fun isWifiConnected(context: Context): Boolean {
        return getConnectedType(context) == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 移动网络是否连接了
     *
     * @param context
     * @return 这些方法返回的信息是有优先级的，例如：同时连接了移动网络，Wifi，这个方法返回false
     */
    fun isMobileConnected(context: Context): Boolean {
        return getConnectedType(context) == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * 获取网络连接类型
     *
     * @param context
     * @return
     */
    fun getConnectedType(context: Context?): Int {
        if (context != null) {
            val connectivityManager = getConnectivityManager(context)
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isAvailable) {
                return networkInfo.type
            }
        }
        return -1
    }

    fun getConnectivityManager(context: Context): ConnectivityManager {
        return context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}