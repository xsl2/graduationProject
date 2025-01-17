package com.example.superui.process

import android.os.Process

object SuperProcessUtil {
    //终结当前应用，只能当前应用ID，因为没root权限
    fun killApp()=Process.killProcess(Process.myPid())
}