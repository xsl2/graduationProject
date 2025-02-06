package com.example.schoolhelper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.schoolhelper.util.ReflectUtil

/**
 * VB : ViewBinding 是一个 泛型约束，指定了类 VB 必须是 ViewBinding 的子类型。
 * ViewBinding 是 Android Jetpack 提供的一种自动生成的类，它提供对布局文件中视图的引用，帮助减少 findViewById 的使用，提高代码的类型安全。
 *
 */
abstract class BaseViewModelFragment<VB : ViewBinding> : BaseLogicFragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ReflectUtil.newViewBinding(layoutInflater, this.javaClass)
    //layoutInflater 是从 Fragment 的上下文中获取的，它是用于将 XML 布局文件转换为 View 对象的工具。每个 Fragment 和 Activity 都有一个 LayoutInflater 实例，可以用来执行布局文件的解析操作。
    //在 Fragment 中，layoutInflater 会通过 onCreateView() 方法的参数传递，或者可以直接通过 getLayoutInflater() 来获取。
    }

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}