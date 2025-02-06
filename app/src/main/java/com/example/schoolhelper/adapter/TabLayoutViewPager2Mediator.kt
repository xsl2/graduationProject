package com.example.schoolhelper.adapter

import androidx.viewpager2.widget.ViewPager2
import com.example.schoolhelper.util.DataUtil
import com.google.android.material.tabs.TabLayout

/**
 * TabLayoutViewPager2Mediator 是一个用于同步 TabLayout 和 ViewPager2 选择状态的中间类。
 */
class TabLayoutViewPager2Mediator(
    private val indicator: TabLayout,
    private val pager: ViewPager2,
    private val config: ((indicator: TabLayout, pager: ViewPager2) -> Unit)? = null
) {
    init {
        val adapterCount: Int = DataUtil.categories.size
        for (i in 0 until adapterCount) {
            //遍历 DataUtil.categories 列表，为每个分类在 TabLayout 中添加一个新的标签（Tab），并将标签的文本设置为对应分类的 title
            indicator.addTab(indicator.newTab().setText(DataUtil.categories.get(i).title), false)
        }

        indicator.selectTab(indicator.getTabAt(0))//默认选中0位置table
    }

    /**
     * 关联BottomNavigationView和ViewPager2的选择关系
     */
    fun attach() {
        config?.invoke(indicator, pager)
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator.selectTab(indicator.getTabAt(position))
            }
        })

        indicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val index = tab.position
                if (pager.currentItem != index) {
                    pager.setCurrentItem(index, false)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }

        })
    }
}