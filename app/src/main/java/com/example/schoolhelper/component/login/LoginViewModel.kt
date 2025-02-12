package com.example.schoolhelper.component.login

import com.example.schoolhelper.R
import com.example.schoolhelper.component.user.User
import com.example.schoolhelper.entity.response.onSuccess
import com.example.schoolhelper.model.BaseViewModel
import com.example.schoolhelper.repository.DefaultNetworkRepository
import com.example.schoolhelper.util.PreferenceUtil
import com.example.super_k.util.StringUtil
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils

/**
 * 登录界面ViewModel
 */
class LoginViewModel() : BaseViewModel() {
    fun login(username: String, password: String) {
        if (StringUtils.isBlank(username)) {
            _tip.value = R.string.enter_phone_or_email

            //返回
            return
        }

        //如果用户名
        //不是手机号也不是邮箱
        //就是格式错误
//        if (!(SuperRegularUtil.isPhone(username) || SuperRegularUtil.isEmail(username))) {
//            _tip.value = R.string.error_username_format
//            return
//        }

        if (TextUtils.isEmpty(password)) {
            _tip.value = R.string.enter_password
            return
        }

        //判断密码格式
        if (!StringUtil.isPassword(password)) {
            _tip.value = R.string.error_password_format
            return
        }

        val user = User()

        //判断是手机号还有邮箱
//        if (SuperRegularUtil.isPhone(username)) {
        if (true) {
            //手机号
            user.phone = username
        } else {
            //邮箱
            user.email = username
        }

        user.password = password

        login(user)
    }

    private fun login(data: User) {
        viewModelScope.launch(coroutineExceptionHandler) {
            DefaultNetworkRepository.login(data).onSuccess(viewModel) {
                //保存用户id
                PreferenceUtil.setUserId(it!!.userId)
                PreferenceUtil.setToken(it!!.session)

                //聊天token
                PreferenceUtil.setChatToken(it!!.chatToken)

                _success.value = it!!.session
            }
        }
    }

    //成功
    private val _success = MutableLiveData<String>()
    val success: LiveData<String> = _success
}