package com.example.schoolhelper.component.content

import android.os.Bundle
import com.example.schoolhelper.databinding.FragmentContentBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment
import com.example.schoolhelper.util.Constant

class ContentFragment:BaseViewModelFragment<FragmentContentBinding>() {


    companion object{
        fun newInstance(categoryID:String?=null):ContentFragment{
            val args = Bundle()
            categoryID?.let {
                args.putString(Constant.ID,it)
            }
            val fragment = ContentFragment()
            fragment.arguments = args
            return fragment
        }
    }

}