package com.example.schoolhelper.component.content

import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolhelper.entity.response.Meta
import com.example.schoolhelper.entity.response.onSuccess
import com.example.schoolhelper.model.BaseViewModel
import com.example.schoolhelper.repository.DefaultNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils

/**
 * 内容界面ViewMode，初入ViewModel
 */
class ContentViewModel(private val categoryId:String?):BaseViewModel() {
    var lastId:String?=null
    //_data 是私有的 MutableSharedFlow，外部无法直接修改它，只能通过类内部的方法来更新数据。
    //data 是公开的 Flow，外部只能订阅数据流，而不能直接修改数据。
    private val _data = MutableSharedFlow<Meta<Content>>()
    val data:Flow<Meta<Content>> = _data

    private val _toArticleDetail = MutableSharedFlow<String>()
    val toArticleDetail: SharedFlow<String> = _toArticleDetail

    private val _toCourseDetail = MutableSharedFlow<String>()
    val toCourseDetail: SharedFlow<String> = _toCourseDetail

    private val _previewMedia = MutableSharedFlow<PreviewMediaPageData>()
    val previewMedia: Flow<PreviewMediaPageData> = _previewMedia

    fun addMore(lastId:String?=null) {
        this.lastId=lastId
        //请求数据
        //DefaultNetWorkReposity.contents(lastID)因为这是一个挂起函数，需要在协程中实现，在viewmodel中用viewModelScope.launch
        viewModelScope.launch(coroutineExceptionHandler) {
            DefaultNetworkRepository.contents(lastId, categoryId = categoryId)
                .onSuccess(viewModel) {
                    _data.emit(it)
                }
        }
    }

    /**
     * 列表item点击事件监听
     */
    fun itemClick(data: Content) {
        viewModelScope.launch {
            if (StringUtils.isNotBlank(data.uri)) {
                _toCourseDetail.emit(data.id!!)
            } else {
                _toArticleDetail.emit(data.id!!)
            }
        }
    }

    fun previewMedias(view: RecyclerView, medias: List<String>, position: Int) {
        viewModelScope.launch {
            _previewMedia.emit(
                PreviewMediaPageData(
                    view,
                    medias,
                    position
                )
            )
        }
    }


}