package com.example.schoolhelper.component.guide

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.schoolhelper.adapter.BaseFragmentPagerAdapter

class GuideAdapter(context:Context,fragmentManager:FragmentManager): BaseFragmentPagerAdapter<Int>(context,fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return GuideFragment.newInstance(getData(position))
    }
}