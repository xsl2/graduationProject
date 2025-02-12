package com.example.schoolhelper.component.login

import android.os.Bundle
import android.text.Html
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drake.channel.receiveEvent
import com.example.schoolhelper.R
import com.example.schoolhelper.activity.BaseTitleActivity
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.databinding.ActivityLoginHomeBinding
import com.example.schoolhelper.util.Constant
import com.example.superui.util.SuperTextUtil

/**
 * 登录主界面
 */
class LoginHomeActivity : BaseTitleActivity<ActivityLoginHomeBinding>() {
    override fun initDatum() {
        super.initDatum()
        SuperTextUtil.setLinkColor(
            binding.userAgreement,
            ContextCompat.getColor(hostActivity, R.color.link)
        )

        val content = Html.fromHtml(getString(R.string.user_agreement))
        binding.userAgreement.text = content

        receiveEvent<LoginStatusChangedEvent> {
            finish()
        }
    }

    override fun initListeners() {
        super.initListeners()
        binding.usernameLogin.setOnClickListener { v -> startActivity(LoginActivity::class.java) }
    }
}