package com.example.schoolhelper.config

import com.example.schoolhelper.BuildConfig

object Config {
    val DEBUG: Boolean=BuildConfig.DEBUG
    const val NETWORK_CACHE_SIZE = (1024*1024*100).toLong()
    const val ENDPOINT=BuildConfig.ENDPOINT
    var RESOURCE_ENDPOINT=BuildConfig.RESOURCE_ENDPOINT
}