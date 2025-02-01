package com.example.schoolhelper.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 所有Activity的父类
 */
open class BaseActivity :AppCompatActivity(){
    protected open fun initViews(){}
    protected open fun initDatum(){}
    protected open fun initListeners(){}
//      它在 onCreate() 完成后被调用，但它的调用顺序比 onResume() 要晚一些
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initViews()
        initDatum()
        initListeners()
    }
}