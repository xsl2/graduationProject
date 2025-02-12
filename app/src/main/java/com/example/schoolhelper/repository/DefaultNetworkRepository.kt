package com.example.schoolhelper.repository

import com.example.schoolhelper.component.api.DefaultNetworkService
import com.example.schoolhelper.component.comment.Comment
import com.example.schoolhelper.component.content.Content
import com.example.schoolhelper.component.login.Session
import com.example.schoolhelper.component.user.User
import com.example.schoolhelper.entity.response.DetailResponse
import com.example.schoolhelper.entity.response.ListResponse

/**
 * 网络数据仓库，model层？
 */
object DefaultNetworkRepository {
    /**
     * 保持单列模式-懒加载
     * 只有第一次访问这个数据时才会加载下面代码块
     */
    private val service:DefaultNetworkService by lazy {
        DefaultNetworkService.create()
    }

    suspend fun userDetail(id: String): DetailResponse<User> {
        return service.userDetail(id)
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
    suspend fun contentDetail(id: String): DetailResponse<Content> {
        return service.contentDetail(id)
    }
    suspend fun login(data: User): DetailResponse<Session> {
        return service.login(data)
    }


    suspend fun comments(
        articleId: String? = null,
        parentId: String? = null,
        page: Int = 1,
        size: Int = 10
    ): ListResponse<Comment> {
        return service.comments(articleId, parentId, page, size)
    }



}