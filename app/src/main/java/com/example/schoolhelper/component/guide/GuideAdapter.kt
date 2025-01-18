package com.example.schoolhelper.component.guide

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class GuideAdapter(val context:Context,fragmentManager:FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    private var datum:MutableList<Int> = mutableListOf()
    override fun getCount(): Int {

        return datum.size
    }

    override fun getItem(position: Int): Fragment {
        return GuideFragment.newInstance(datum[position])

    }

    fun setDatum(datum: MutableList<Int>) {
        this.datum.clear()
        this.datum.addAll(datum)
        notifyDataSetChanged()
    }

}