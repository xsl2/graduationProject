package com.example.schoolhelper.component.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import com.example.schoolhelper.R
import com.example.schoolhelper.activity.BaseLogicActivity
import com.example.superui.date.SuperDateUtil
import com.example.superui.util.SuperDarkUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
/**
 * 启动界面
 *
 */
class SplashActivity : BaseLogicActivity() {
    private lateinit var tv_author:TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showTermServiceAgreementDialog()

    }

    override fun initViews() {
        super.initViews()
        //设置全屏，沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)
        if (SuperDarkUtil.isDark(this)) QMUIStatusBarHelper.setStatusBarDarkMode(this) else QMUIStatusBarHelper.setStatusBarLightMode(this)
        tv_author=findViewById<TextView>(R.id.tv_author)
    }

    override fun initDatum() {
        super.initDatum()
        tv_author.text = "designed by xsl ${SuperDateUtil.currentYear()}"
    }
    private fun showTermServiceAgreementDialog()
    {
        TermServiceDialogFragment.show(supportFragmentManager)
    }
}