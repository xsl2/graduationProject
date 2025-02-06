package com.example.schoolhelper.component.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolhelper.entity.response.Meta
import com.example.schoolhelper.repository.DefaultNetWorkReposity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * 内容界面ViewMode，初入ViewModel
 */
class ContentViewModel:ViewModel() {
    //_data 是私有的 MutableSharedFlow，外部无法直接修改它，只能通过类内部的方法来更新数据。
    //data 是公开的 Flow，外部只能订阅数据流，而不能直接修改数据。
    private val _data = MutableSharedFlow<Meta<Content>>()
    val data:Flow<Meta<Content>> = _data

    fun addMore(lastID:String?=null) {
        //请求数据
        //DefaultNetWorkReposity.contents(lastID)因为这是一个挂起函数，需要在协程中实现，在viewmodel中用viewModelScope.launch
        viewModelScope.launch {
            val r = DefaultNetWorkReposity.contents(lastID)
            //现在需要把r的数据传回Fragment，可以使用回调，这里也可以使用LiveData，也可以使用flow
            _data.emit(r.data!!)
        }
    }


}