package com.example.schoolhelper.component.main

import androidx.core.view.GravityCompat
import androidx.viewpager2.widget.ViewPager2
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.example.schoolhelper.R
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.component.login.LoginHomeActivity
import com.example.schoolhelper.component.userdetail.UserDetailActivity
import com.example.schoolhelper.databinding.ActivityMainBinding
import com.example.schoolhelper.databinding.ItemTabBinding
import com.example.schoolhelper.util.Constant
import com.example.schoolhelper.util.PreferenceUtil
import com.example.superui.process.SuperProcessUtil
import com.example.superui.util.SuperDarkUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class MainActivity : BaseViewModelActivity<ActivityMainBinding>() {


    override fun initViews() {
        super.initViews()
        //设置全屏，沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)
//        if (SuperDarkUtil.isDark(this)) QMUIStatusBarHelper.setStatusBarDarkMode(this) else QMUIStatusBarHelper.setStatusBarLightMode(this)
//        tv_author=findViewById<TextView>(R.id.tv_author)
    }

    override fun initListeners() {
        super.initListeners()
        binding.content.pager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position)
                {
                    0,1->QMUIStatusBarHelper.setStatusBarDarkMode(hostActivity)
                    else->QMUIStatusBarHelper.setStatusBarLightMode(hostActivity)
                }
            }
        })
        //关闭应用点击
        binding.closeApp.setOnClickListener {
            SuperProcessUtil.killApp()
        }
        //用户容器点击
        binding.userContainer.setOnClickListener {
            closeDrawer()
            if (PreferenceUtil.isLogin()) {
                startActivityExtraId(UserDetailActivity::class.java, PreferenceUtil.getUserId())
            } else {
                startActivity(LoginHomeActivity::class.java)
            }
        }
    }

    override fun initDatum() {
        super.initDatum()
        //滚动控件
        binding.content.apply {
            pager.offscreenPageLimit= indicatorTitles.size
            pager.adapter=MainAdapter(this@MainActivity, indicatorTitles.size)
        }

        //底部tab
        for (i in indicatorTitles.indices) {
            ItemTabBinding.inflate(layoutInflater).apply {
                content.setText(indicatorTitles[i])
                icon.setImageResource(indicatorIcons[i])
                binding.content.indicator.addView(root)
            }
        }
        ViewPager2Delegate.install(binding.content.pager, binding.content.indicator, false)



        val action=intent.action
        if(Constant.ACTION_LOGIN==action)
        {
            startActivity(LoginHomeActivity::class.java)
        }

    }

    fun openDrawer() {
        binding.drawer.openDrawer(GravityCompat.START)
    }

    fun closeDrawer(): Unit {
        binding.drawer.closeDrawer(GravityCompat.START)
    }


    companion object {
        /**
         * 底部指示器（tab）文本，图标，选中的图标
         */
        private val indicatorTitles =
            intArrayOf(
                R.string.discovery,
                R.string.video,
                R.string.category,
                R.string.me
            )
        private val indicatorIcons = intArrayOf(
            R.drawable.selector_tab_discovery,
            R.drawable.selector_tab_video,
            R.drawable.selector_tab_category,
            R.drawable.selector_tab_me
        )
    }
}