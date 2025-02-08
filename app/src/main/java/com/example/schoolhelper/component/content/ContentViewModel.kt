package com.example.schoolhelper.component.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolhelper.R
import com.example.schoolhelper.component.category.Category
import com.example.schoolhelper.entity.response.Meta
import com.example.schoolhelper.model.BaseViewModel
import com.example.schoolhelper.repository.DefaultNetWorkReposity
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
        viewModelScope.launch {
            try {
                val r = DefaultNetWorkReposity.contents(lastId,categoryId = categoryId)
                //现在需要把r的数据传回Fragment，可以使用回调，这里也可以使用LiveData，也可以使用flow
                if(r.isSucceeded){
                    _data.emit(r.data!!)
                }else{
                    _response.value=r
                }
            } catch (e: Exception) {
                _exception.value=e
            }

        }
    }

    /**
     * 列表item点击
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