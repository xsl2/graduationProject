package com.example.schoolhelper.util

import com.tencent.mmkv.MMKV

object PreferenceUtil {
    val p: MMKV by lazy {
        MMKV.defaultMMKV()!!
    }

    /**
     * 是否显示引导界面
     *
     * @return
     */
    fun isShowGuide(): Boolean {
        return getBoolean(SHOW_GUIDE, true)
    }

    /**
     * 设置是否显示引导界面
     *
     * @param value
     */
    fun setShowGuide(value: Boolean) {
        putBoolean(SHOW_GUIDE, value)
    }

    /**
     * 是否登录了
     *
     * @return
     */
    fun isLogin(): Boolean {
        return Constant.ANONYMOUS != getUserId()
    }

    /**
     * 设置用户Id
     *
     * @param value
     */
    fun setUserId(value: String?) {
        p.encode(USER_ID, value)
    }

    /**
     * 获取用户Id
     *
     * @return
     */
    fun getUserId(): String {
        return p.decodeString(USER_ID, Constant.ANONYMOUS)!!
    }

    /**
     * 设置用户Token
     *
     * 可以加密后存储，防止泄露
     * @param value
     */
    fun setToken(value: String?) {
        p.encode(TOKEN, value)
    }

    /**
     * 获取用户Token
     *
     * @return
     */
    fun getToken(): String? {
        return p.decodeString(TOKEN)
    }

    /**
     * 设置用户Chat Token
     *
     * @param value
     */
    fun setChatToken(value: String) {
        p.encode(CHAT_TOKEN, value)
    }

    /**
     * 获取用户Chat Token
     *
     * @return
     */
    fun getChatToken(): String {
        return p.decodeString(CHAT_TOKEN)!!
    }

    /**
     * 退出
     */
    fun logout() {
        p.removeValueForKey(USER_ID)
        p.removeValueForKey(TOKEN)
        p.removeValueForKey(CHAT_TOKEN)
    }

    /**
     * 获取启动界面广告
     *
     * @return
     */
//    fun getSplashAd(): Ad? {
//        val result = p.getString(SPLASH_AD, null)
//        return if (StringUtils.isBlank(result)) {
//            null
//        } else JSONUtil.fromJSON(result!!, Ad::class.java)
//    }

    /**
     * 设置启动界面广告
     *
     * @param data 如果为空，就是删除本地广告
     */
//    fun setSplashAd(data: Ad?) {
//        if (null == data) {
//            delete(SPLASH_AD)
//        } else {
//            putString(SPLASH_AD, JSONUtil.toJSON(data))
//        }
//    }

//    fun getDeviceId(): String {
//        var id = getString(KEY_DEVICE_ID)
//        if (StringUtils.isBlank(id)) {
//            id = SuperUUIDUtil.uuid
//            putString(KEY_DEVICE_ID, id)
//        }
//        return id!!
//    }

    //region 辅助方法
    private fun getString(key: String): String? {
        return p.decodeString(key, null)
    }

    private fun putString(key: String, value: String) {
        p.edit().putString(key, value).apply()
    }

    private fun delete(data: String) {
        p.edit().remove(data).commit()
    }


    /**
     * 获取boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return p.getBoolean(key, defaultValue)
    }

    /**
     * 保存boolean
     *
     * @param key
     * @param value
     */
    private fun putBoolean(key: String, value: Boolean) {
        p.edit().putBoolean(key, value).apply()
    }

    //endregion

    /**
     * 是否显示引导界面key
     */
    private const val SHOW_GUIDE = "SHOW_GUIDE"
    private const val USER_ID = "user"
    private const val TOKEN = "token"
    private const val CHAT_TOKEN = "CHAT_TOKEN"
    private const val SPLASH_AD = "SPLASH_AD"
    private const val KEY_DEVICE_ID = "KEY_DEVICE_ID"
}