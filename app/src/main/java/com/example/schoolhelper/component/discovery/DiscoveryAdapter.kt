package com.example.schoolhelper.component.discovery

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.schoolhelper.adapter.BaseFragmentPagerAdapter
import com.example.schoolhelper.component.category.Category
import com.example.schoolhelper.component.content.ContentFragment

class DiscoveryAdapter(fragmentActivity: FragmentActivity, private val datum:List<Category>):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return datum.size
    }

    override fun createFragment(position: Int): Fragment {
        return ContentFragment.newInstance(datum[position].id)
    }

}