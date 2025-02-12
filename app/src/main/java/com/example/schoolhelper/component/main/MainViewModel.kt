package com.example.schoolhelper.component.main

import androidx.lifecycle.viewModelScope
import com.example.schoolhelper.component.user.User
import com.example.schoolhelper.entity.response.onSuccess
import com.example.schoolhelper.model.BaseViewModel
import com.example.schoolhelper.repository.DefaultNetworkRepository
import com.example.schoolhelper.util.PreferenceUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


/**
 * 首页VM
 */
class MainViewModel : BaseViewModel() {
    private val _userData = MutableSharedFlow<User>()
    val userData: Flow<User> = _userData

    fun loadUserData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            DefaultNetworkRepository.userDetail(PreferenceUtil.getUserId()).onSuccess(viewModel) {
                _userData.emit(it!!)
            }
        }
    }
}