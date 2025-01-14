package com.example.superui.date

import android.icu.util.Calendar
import java.time.Year

object SuperDateUtil {
    /**
     * 获取年份
     * @author xsl
     * @return Int
     * @since 2025.1.13
     */
     fun currentYear():Int=Calendar.getInstance().get(Calendar.YEAR)
}