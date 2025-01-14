package com.example.superui.util

import android.content.Context
import android.content.res.Configuration

object SuperDarkUtil {
    fun isDark(content: Context):Boolean=content.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}