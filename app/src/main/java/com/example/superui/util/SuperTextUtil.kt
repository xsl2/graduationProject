package com.example.superui.util

import android.text.method.LinkMovementMethod
import android.widget.TextView

object SuperTextUtil {
    fun setLinkColor(view:TextView,color: Int){
        view.movementMethod=LinkMovementMethod.getInstance()
        view.setLinkTextColor(color)
    }
}