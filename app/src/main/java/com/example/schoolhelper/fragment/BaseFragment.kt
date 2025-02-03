package com.example.schoolhelper.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open abstract class BaseFragment : Fragment() {
    protected open fun initViews(){}//子类可以重写它来初始化视图组件
    protected open fun initDatum(){}//用于处理数据初始化的逻辑。
    protected open fun initListeners(){}//在子类中重写，设置监听器

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=getLayoutView(inflater, container, savedInstanceState)
        return view
    }

    open abstract fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    /**
     * onViewCreated 是 Fragment 生命周期中的一个方法，它会在 onCreateView 后被调用，确保视图已经被创建并可以进行操作。
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initDatum()
        initListeners()
    }

}