package com.example.schoolhelper.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.schoolhelper.util.ReflectUtil

/**
 * 通用ViewModel Activity
 * 包括ViewBinding，主要是处理每次要setContentView
 * 以及自动创建ViewModel
 * 以及viewModel的通用观察处理
 */
open class BaseViewModelActivity<VB : ViewBinding> : BaseLogicActivity() {
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //调用inflate方法，创建viewBinding
        binding = ReflectUtil.newViewBinding(layoutInflater, javaClass)
//        ReflectUtil.newViewBinding 使用反射来创建 ViewBinding 实例。这使得你可以根据当前 Activity 的类名动态生成相应的 ViewBinding 对象，避免了手动写每个类的 ViewBinding 实例化代码。

        setContentView(binding.root)
    }
}