package com.example.schoolhelper.component.me

import android.os.Bundle
import com.example.schoolhelper.component.guide.GuideFragment
import com.example.schoolhelper.databinding.FragmentHomeBinding
import com.example.schoolhelper.databinding.FragmentMeBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment
import com.example.schoolhelper.util.Constant

class MeFragment : BaseViewModelFragment<FragmentMeBinding>() {

    companion object
    {
        fun newInstance(): MeFragment {
            val args = Bundle()

            val fragment = MeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}