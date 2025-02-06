package com.example.schoolhelper.activity

/**
 * 项目通用逻辑
 */
open class BaseLogicActivity:BaseCommonActivity() {
    protected val hostActivity:BaseLogicActivity
        protected get() = this

}