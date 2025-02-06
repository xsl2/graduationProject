package com.example.schoolhelper.component.discovery

import android.os.Bundle
import com.example.schoolhelper.adapter.TabLayoutViewPager2Mediator
import com.example.schoolhelper.databinding.FragmentDiscoveryBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment
import com.example.schoolhelper.util.DataUtil
import java.util.Date

/**
 * 发现界面的Fragment里面还内嵌了ContentFragment，在Discovery中按categoryID开启Fragment
 */
class DiscoveryFragment : BaseViewModelFragment<FragmentDiscoveryBinding>() {
    override fun initDatum() {
        super.initDatum()
        binding.apply {
            pager.adapter=DiscoveryAdapter(requireActivity(),DataUtil.categories)
            TabLayoutViewPager2Mediator(indicator,pager){_,_->
            }.attach()

        }
    }

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