package com.example.schoolhelper.util

import com.example.schoolhelper.component.category.Category

object DataUtil {
    val categories:List<Category> = listOf(
        Category.create(
            "1", "推荐"
        ), Category.create(
            "2", "发现"
        ), Category.create(
            "3", "每日必看"
        ), Category.create(
            "4", "视频"
        ), Category.create(
            "5", "小说"
        ), Category.create(
            "6", "问答"
        ), Category.create(
            "7", "音乐"
        ), Category.create(
            "8", "发现"
        )
    )
}