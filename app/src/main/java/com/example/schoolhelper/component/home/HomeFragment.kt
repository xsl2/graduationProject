package com.example.schoolhelper.component.home

import android.os.Bundle
import com.example.schoolhelper.component.guide.GuideFragment
import com.example.schoolhelper.databinding.FragmentHomeBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment
import com.example.schoolhelper.util.Constant

class HomeFragment : BaseViewModelFragment<FragmentHomeBinding>() {

    companion object
    {
        fun newInstance(): HomeFragment {
            val args = Bundle()

            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}