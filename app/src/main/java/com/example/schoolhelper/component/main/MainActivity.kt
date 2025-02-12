package com.example.schoolhelper.component.main

import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.example.schoolhelper.AppContext
import com.example.schoolhelper.R
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.component.login.LoginHomeActivity
import com.example.schoolhelper.component.user.User
import com.example.schoolhelper.component.userdetail.UserDetailActivity
import com.example.schoolhelper.databinding.ActivityMainBinding
import com.example.schoolhelper.databinding.ItemTabBinding
import com.example.schoolhelper.util.Constant
import com.example.schoolhelper.util.ImageUtil
import com.example.schoolhelper.util.PreferenceUtil
import com.example.superui.dialog.SuperDialog
import com.example.superui.extension.hide
import com.example.superui.extension.show
import com.example.superui.process.SuperProcessUtil
import com.example.superui.util.SuperDarkUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.coroutines.launch

class MainActivity : BaseViewModelActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel


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

        //退出登录点击
        binding.primary.setOnClickListener { v ->
            //弹出确认对话框
            //防止用户误操作
            //同时我们本质是想留住用户
            SuperDialog.newInstance(supportFragmentManager)
                .setTitleRes(R.string.confirm_logout)
                .setOnClickListener {
                    AppContext.instance.logout()
                    showNotLogin()
                    closeDrawer()
                }.show()
        }
    }

    override fun initDatum() {
        super.initDatum()
        viewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
        initViewModel(viewModel)

        lifecycleScope.launch {
            viewModel.userData
                .collect { data ->
                    showUserData(data)
                }
        }

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
    override fun onResume() {
        super.onResume()
        showUserInfo()
    }

    //region 显示用户信息
    private fun showUserInfo() {
        if (PreferenceUtil.isLogin()) {
            //已经登录了

            //获取用户信息
            viewModel.loadUserData()
            binding.primary.show()
        } else {
            showNotLogin()
        }
    }

    private fun showUserData(data: User) {
        //显示头像
        ImageUtil.showAvatar(binding.avatar, data.icon)

        //显示昵称
        binding.nickname.setText(data.nickname)
    }

    /**
     * 显示未登录状态
     */
    private fun showNotLogin() {
        binding.nickname.setText(R.string.login_or_register)
        binding.avatar.setImageResource(R.drawable.default_avatar)
        binding.primary.hide()
    }

    //endregion


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