package com.example.schoolhelper.component.content

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.schoolhelper.databinding.FragmentContentBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment
import com.example.schoolhelper.util.Constant
import kotlinx.coroutines.launch

class ContentFragment:BaseViewModelFragment<FragmentContentBinding>() {
    private lateinit var viewModel: ContentViewModel

    /**
     * 下面开始第一次使用MVVM架构，学习viewmodel
     * 也开始学习lifecycle协程。
     */
    override fun initDatum() {
        super.initDatum()
        //MVC架构就直接在这里面写，这里是C-controller层，进行MV层交互
//        lifecycleScope.launch {
//            DefaultNetWorkReposity.contents()
//        }
        viewModel=ViewModelProvider(this).get(ContentViewModel::class.java)
        lifecycleScope.launch {
            viewModel.data.collect{
                Log.d(TAG, "initDatum: ${it.data!![0].title}")
            }
        }
        viewModel.addMore()
    }


    companion object{
        const val TAG="ContentFragment"

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