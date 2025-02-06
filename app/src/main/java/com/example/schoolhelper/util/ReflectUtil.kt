package com.example.schoolhelper.util

import android.view.LayoutInflater
import java.lang.reflect.ParameterizedType

/**
 * 反射工具类
 */
object ReflectUtil {
    /**
     * 创建view binding
     */
    fun <VB> newViewBinding(layoutInflater: LayoutInflater, clazz: Class<*>): VB {
        return try {
            //获取泛型参数对象
            val type = try {
                clazz.genericSuperclass as ParameterizedType//获取的是 VB 的超类的泛型类型
            // ParameterizedType 表示一个参数化类型，它允许我们访问泛型参数的信息，比如 VB
            } catch (e: ClassCastException) {
                clazz.superclass.genericSuperclass as ParameterizedType
            }

            //type.actualTypeArguments[0]：ViewBinding
            val clazzVB = type.actualTypeArguments[0] as Class<VB>

            //获取inflate方法
            val inflateMethod = clazzVB.getMethod("inflate", LayoutInflater::class.java)
            inflateMethod.invoke(null, layoutInflater) as VB
            //通过反射调用 inflate 方法，null 表示我们在调用静态方法时不需要提供实例对象，layoutInflater 是传递给 inflate 方法的参数。
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }
}