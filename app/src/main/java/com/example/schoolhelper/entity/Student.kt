package com.example.schoolhelper.entity

import kotlin.math.max

class Student {
    var account=""
    var password=""
    override
    fun toString(): String {
        return "Student(account='$account', password='$password')"
    }

}
fun max1(num1:Int,num2:Int):Int= max(num1,num2)
fun main()
{
    var a=10
    var b=20
    var ans= max1(a,b)
    println("hello kotlin"+ans)
}