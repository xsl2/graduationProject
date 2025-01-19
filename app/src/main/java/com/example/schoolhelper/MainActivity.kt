package com.example.schoolhelper

import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.component.login.LoginHomeActivity
import com.example.schoolhelper.databinding.ActivityMainBinding
import com.example.schoolhelper.util.Constant

class MainActivity : BaseViewModelActivity<ActivityMainBinding>() {
    override fun initDatum() {
        super.initDatum()
        val action=intent.action
        if(Constant.ACTION_LOGIN==action)
        {
            startActivity(LoginHomeActivity::class.java)
        }


    }
}