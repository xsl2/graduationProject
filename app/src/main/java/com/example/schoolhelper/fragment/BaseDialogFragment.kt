package com.example.schoolhelper.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment

open abstract class BaseDialogFragment :DialogFragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initDatum()
        initListeners()
    }


    fun <T : View?> findViewById(@IdRes id: Int): T {
        return requireView().findViewById(id)
    }
}