package com.example.schoolhelper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

open abstract class BaseDialogFragment :DialogFragment() {
    protected open fun initViews(){}
    protected open fun initDatum(){}
    protected open fun initListeners(){}

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

}