package com.example.schoolhelper.component.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.schoolhelper.component.home.HomeFragment
import com.example.schoolhelper.component.me.MeFragment

class MainAdapter(fragmentActivity: MainActivity,val count:Int):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            1->MeFragment.newInstance()
            else->HomeFragment.newInstance()
        }
//        return HomeFragment.newInstance()
    }
}