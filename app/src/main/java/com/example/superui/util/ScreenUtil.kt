package com.example.superui.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * 屏幕工具类
 */
object ScreenUtil {

    // 获取屏幕的DisplayMetrics信息
    private fun getDisplayMetrics(context: Context): DisplayMetrics {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outDisplayMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outDisplayMetrics)
        return outDisplayMetrics
    }

    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度（像素）
     */
    fun getScreenWidth(context: Context): Int {
        return getDisplayMetrics(context).widthPixels
    }

    /**
     * 获取屏幕高度
     *
     * @param context 上下文
     * @return 屏幕高度（像素）
     */
    fun getScreenHeight(context: Context): Int {
        return getDisplayMetrics(context).heightPixels
    }

    /**
     * 获取屏幕的密度
     *
     * @param context 上下文
     * @return 屏幕密度（如：2.0）
     */
    fun getScreenDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }
}