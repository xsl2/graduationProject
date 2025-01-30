package com.example.schoolhelper.component.main

import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.example.schoolhelper.R
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.component.login.LoginHomeActivity
import com.example.schoolhelper.databinding.ActivityMainBinding
import com.example.schoolhelper.databinding.ItemTabBinding
import com.example.schoolhelper.util.Constant

class MainActivity : BaseViewModelActivity<ActivityMainBinding>() {
    override fun initDatum() {
        binding.apply {
            pager.offscreenPageLimit= indicatorTitles.size
            pager.adapter=MainAdapter(this@MainActivity, indicatorTitles.size)
        }

        //底部tab
        for (i in indicatorTitles.indices) {
            ItemTabBinding.inflate(layoutInflater).apply {
                content.setText(indicatorTitles[i])
                icon.setImageResource(indicatorIcons[i])
                binding.indicator.addView(root)
            }
        }
//        ViewPager2Delegate.install(binding.pager,binding.indicator,false)

        super.initDatum()
        val action=intent.action
        if(Constant.ACTION_LOGIN==action)
        {
            startActivity(LoginHomeActivity::class.java)
        }
    }

    companion object {
        /**
         * 底部指示器（tab）文本，图标，选中的图标
         */
        private val indicatorTitles =
            intArrayOf(
                R.string.discovery,
//                R.string.video,
//                R.string.category,
                R.string.me
            )
        private val indicatorIcons = intArrayOf(
            R.drawable.selector_tab_discovery,
//            R.drawable.selector_tab_video,
//            R.drawable.selector_tab_category,
            R.drawable.selector_tab_me
        )
    }
}