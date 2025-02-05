package com.example.schoolhelper.component.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.schoolhelper.component.category.CategoryFragment
import com.example.schoolhelper.component.discovery.DiscoveryFragment
import com.example.schoolhelper.component.me.MeFragment
import com.example.schoolhelper.component.shortvideo.ShortVideoFragment

class MainAdapter(fragmentActivity: FragmentActivity, val count:Int):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            1 -> ShortVideoFragment.newInstance()
            2 -> CategoryFragment.newInstance()
            3 -> MeFragment.newInstance()
            else -> DiscoveryFragment.newInstance()
        }
    }
}