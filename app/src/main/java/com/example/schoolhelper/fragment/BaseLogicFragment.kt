package com.example.schoolhelper.fragment

import com.example.schoolhelper.activity.BaseLogicActivity

abstract class BaseLogicFragment:BaseCommonFragment() {
    /**
     * 获取界面方法
     *
     * @return
     */
    protected val hostActivity: BaseLogicActivity
        protected get() = requireActivity() as BaseLogicActivity
}