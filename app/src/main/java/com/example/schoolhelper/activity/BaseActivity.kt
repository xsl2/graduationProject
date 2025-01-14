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

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initViews()
        initDatum()
        initListeners()
    }
}