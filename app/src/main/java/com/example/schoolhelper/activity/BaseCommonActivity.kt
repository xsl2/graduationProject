package com.example.schoolhelper.activity

import android.content.Intent
import com.example.schoolhelper.util.Constant

open class BaseCommonActivity :BaseActivity() {

    /**
     * 启动界面
     * @param clazz
     */
    protected fun startActivity(clazz: Class<*>) {
        //创建Intent
        val intent = Intent(this, clazz)

        //启动界面
        startActivity(intent)
    }

    /**
     * 启动界面并关闭当前界面
     * @param clazz
     */
    fun startActivityAfterFinishThis(clazz: Class<*>) {
        startActivity(clazz)
        //关闭当前界面
        finish()
    }


    /**
     * 启动界面，可以传递一个字符串参数
     *
     * @param clazz
     * @param id
     */
    protected fun startActivityExtraId(
        clazz: Class<*>,
        id: String
    ) {
        //创建Intent
        val intent = Intent(this, clazz).apply {
            //传递数据
            //不为空才传递
            putExtra(Constant.ID, id)
        }

        //启动界面
        startActivity(intent)
    }
}