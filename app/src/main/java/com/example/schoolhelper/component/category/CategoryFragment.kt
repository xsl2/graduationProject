package com.example.schoolhelper.component.category

import android.os.Bundle
import com.example.schoolhelper.databinding.FragmentCategoryBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment

class CategoryFragment : BaseViewModelFragment<FragmentCategoryBinding>() {

    companion object
    {
        fun newInstance(): CategoryFragment {
            val args = Bundle()
            val fragment = CategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }
}