package com.example.schoolhelper.component.discovery

import android.os.Bundle
import com.example.schoolhelper.databinding.FragmentDiscoveryBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment

class DiscoveryFragment : BaseViewModelFragment<FragmentDiscoveryBinding>() {

    companion object
    {
        fun newInstance(): DiscoveryFragment {
            val args = Bundle()
            val fragment = DiscoveryFragment()
            fragment.arguments = args
            return fragment
        }
    }
}