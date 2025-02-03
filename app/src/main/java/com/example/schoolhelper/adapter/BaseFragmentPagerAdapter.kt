package com.example.schoolhelper.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * 通用FragmentPagerAdapter
 * 主要是创建了列表实现了通用的方法
 * 直接用FragmentPagerAdapter有可能有内存泄漏
 * 所以使用FragmentStatePagerAdapter
 * FragmentStatePagerAdapter 和 FragmentPagerAdapter 的区别在于：
 *
 * FragmentPagerAdapter 会将每个 Fragment 保留在内存中，适用于数据较少、页面较少的场景。但是，当页面数量较多时，FragmentPagerAdapter 会导致内存消耗较大。
 * FragmentStatePagerAdapter 会根据需要销毁不在当前显示范围的 Fragment，仅保留当前显示的 Fragment，当 Fragment 被滑出视野时，它会被销毁，避免内存泄漏和过高的内存占用。这种方式适用于数据量较大、页面较多的场景。
 */
abstract class BaseFragmentPagerAdapter<T>(val context: Context, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    private var datum: MutableList<T> = mutableListOf()

    /**
     * 有多少个fragment
     *
     * @return
     */
    override fun getCount(): Int {
        return datum.size
    }

    /**
     * 设置fragmentlist数据
     *
     * @param datum
     */
    fun setDatum(datum: MutableList<T>) {
        this.datum.clear()
        this.datum.addAll(datum)
        notifyDataSetChanged()
    }

    /**
     * 获取当前位置的数据
     *
     * @param position
     * @return
     */
    fun getData(position: Int): T {
        return datum[position]
    }
}