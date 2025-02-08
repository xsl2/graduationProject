package com.example.schoolhelper.activity

import com.example.schoolhelper.AppContext
import com.example.schoolhelper.R
import com.example.schoolhelper.entity.response.BaseResponse
import com.example.superui.extension.longToast
import com.example.superui.extension.shortToast
import com.example.superui.util.SuperNetworkUtil
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * 项目通用逻辑
 */
open class BaseLogicActivity:BaseCommonActivity() {
    protected val hostActivity:BaseLogicActivity
        protected get() = this


    open fun onTip(data: Int) {
        data.shortToast()
        onError()
    }

    open fun onResponse(data: BaseResponse) {
        when (data.status) {
            401 -> {
                R.string.error_not_auth.longToast()
                AppContext.instance.logout()
            }

            403 -> {
                R.string.error_not_permission.longToast()
            }

            404 -> {
                R.string.error_not_found.longToast()
            }
        }
        (data.message ?: getString(R.string.error_unknown)).longToast()
        onError()
    }

    open fun onException(data: Throwable) {
//        if (NetworkUtils.isAvailableByPing()) {
//            //有网络
//            R.string.error_load.longToast()
//        } else {
//            //提示，你的网络好像不太好
//            R.string.error_network_not_connect.longToast()
//        }

        when (data) {
            is SocketException -> {
                //例如：服务器没有启动
                R.string.error_connect_server.longToast()
            }

            is UnknownHostException -> {
                //域名无法解析，例如：域名写错了
                R.string.error_unknown_host.longToast()
            }

            is SocketTimeoutException -> {
                //连接超时，例如：网络特别慢
                R.string.error_network_timeout.longToast()
            }

            is ConnectException -> {
                //以下情况都会触发该异常：
                //服务器没有开启
                //本地网络关闭
                if (SuperNetworkUtil.isNetworkConnected(hostActivity)) {
                    //本地有网络

                    //提示连接服务端失败
                    R.string.error_connect_server.longToast()
                } else {
                    //本地没有网络

                    //提示，你的网络好像不太好
                    R.string.error_network_not_connect.longToast()
                }
            }

            is HttpException -> {
                //http异常，例如：服务端返回401，403
                handleHttpError(data)
            }

            is IllegalArgumentException -> {
                //本地参数错误
                R.string.error_illegal_argument.shortToast()
            }
//            is ClientException -> {
//                "阿里云OSS客户端错误：${data.localizedMessage}".longToast()
//            }
//            is ServiceException -> {
//                "阿里云OSS服务端错误：${data.localizedMessage}".longToast()
//            }
            else -> {
                R.string.error_unknown.shortToast()
            }
        }

        onError()
    }

    private fun handleHttpError(data: HttpException) {
        AppContext.instance.getString(R.string.error_server_unknown_code, data.code()).shortToast()
    }

    open fun onError() {

    }
}