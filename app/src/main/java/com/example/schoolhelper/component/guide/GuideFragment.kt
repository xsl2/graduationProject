package com.example.schoolhelper.component.guide

import android.os.Bundle
import com.example.schoolhelper.databinding.FragmentGuideBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment
import com.example.schoolhelper.util.Constant

class GuideFragment:BaseViewModelFragment<FragmentGuideBinding>() {
    override fun initDatum() {
        super.initDatum()
        val data=requireArguments().getInt(Constant.ID)
        binding.icon.setImageResource(data)
    }
    companion object
    {
        fun newInstance(data:Int):GuideFragment {
            val args = Bundle()
            args.putInt(Constant.ID,data)
            val fragment = GuideFragment()
            fragment.arguments = args
            return fragment
        }
    }



}