package com.example.superui.util

import android.content.Context
import android.content.res.Configuration

object SuperDarkUtil {
//    Context 是 Android 中的上下文对象，它提供了访问资源、系统服务等功能。在这里，我们用它来获取应用的配置。
//    content.resources.configuration 获取到当前应用的 Configuration 对象，它包含了应用的各种配置信息，比如屏幕方向、语言、UI 模式（是否为暗黑模式等）。
//    uiMode 是 Configuration 中的一个字段，表示当前的 UI 模式。
//    下面是先与在判断==操作，不要搞反
    fun isDark(content: Context):Boolean=content.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}