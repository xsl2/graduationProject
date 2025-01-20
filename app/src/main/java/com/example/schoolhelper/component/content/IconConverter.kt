package com.example.schoolhelper.component.content

import androidx.room.TypeConverter

class IconConverter {
    @TypeConverter
    fun getIconFromString(value: String?): List<String>? {
        return value?.split(",")
    }

    @TypeConverter
    fun storeIconToString(list: List<String>?): String? {
        return list?.joinToString(",")
    }
}