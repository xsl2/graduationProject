package com.example.schoolhelper.repository

import com.example.schoolhelper.component.api.DefaultNetworkService
import com.example.schoolhelper.component.content.Content
import com.example.schoolhelper.entity.response.ListResponse

/**
 * 网络数据仓库，model层？
 */
object DefaultNetWorkReposity {
    /**
     * 保持单列模式-懒加载
     * 只有第一次访问这个数据时才会加载下面代码块
     */
    private val service:DefaultNetworkService by lazy {
        DefaultNetworkService.create()
    }
    //挂起函数
    suspend fun contents(
        last: String? = null,
        categoryId: String? = null,
        userId: String? = null,
        size: Int = 10,
        style: Int? = null
    ): ListResponse<Content> {
        return service.contents(last, categoryId, userId, size, style)
    }

}