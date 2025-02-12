package com.example.schoolhelper.component.articledetail

import androidx.lifecycle.viewModelScope
import com.example.schoolhelper.component.comment.Comment
import com.example.schoolhelper.component.content.Content
import com.example.schoolhelper.entity.response.Meta
import com.example.schoolhelper.entity.response.onSuccess
import com.example.schoolhelper.model.BaseViewModel
import com.example.schoolhelper.repository.DefaultNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * 文章详情界面ViewModel
 */
class ArticleDetailViewModel(private val id: String) : BaseViewModel() {
    private val _data = MutableSharedFlow<Content>()
    val data: Flow<Content> = _data

    private val _comments = MutableSharedFlow<Meta<Comment>>()
    val comments: Flow<Meta<Comment>> = _comments

    lateinit var content: Content


    fun loadData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            DefaultNetworkRepository.contentDetail(id)
                .onSuccess(viewModel) {
                    content = it!!
                    _data.emit(it!!)
                }
        }
        loadMoreComment()
    }

    private fun loadMoreComment() {
        viewModelScope.launch(coroutineExceptionHandler) {
            DefaultNetworkRepository.comments(articleId = id)
                .onSuccess(viewModel) {
                    _comments.emit(it)
                }
        }
    }

    fun loadReplyComment(data: Comment) {

    }

}