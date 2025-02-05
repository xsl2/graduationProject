package com.example.schoolhelper.component.shortvideo

import android.os.Bundle
import com.example.schoolhelper.databinding.FragmentShortvideoBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment

class ShortVideoFragment : BaseViewModelFragment<FragmentShortvideoBinding>() {

    companion object
    {
        fun newInstance(): ShortVideoFragment {
            val args = Bundle()

            val fragment = ShortVideoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}