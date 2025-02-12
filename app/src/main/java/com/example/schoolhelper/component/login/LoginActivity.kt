package com.example.schoolhelper.component.login

import androidx.lifecycle.ViewModelProvider
import com.example.schoolhelper.AppContext
import com.example.schoolhelper.activity.BaseTitleActivity
import com.example.schoolhelper.config.Config
import com.example.schoolhelper.databinding.ActivityLoginBinding

/**
 * 登录界面
 */
class LoginActivity : BaseTitleActivity<ActivityLoginBinding>() {
    private lateinit var viewModel: LoginViewModel

    override fun initDatum() {
        super.initDatum()
        if (Config.DEBUG) {
            binding.username.setText("13141111222")
            binding.password.setText("ixueaedu")
        }

        viewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)
        initViewModel(viewModel)

        //观察成功结果
        viewModel.success.observe(this) {
            //成功了

            //执行登录后操作
            AppContext.instance.onLogin()
            finish()
        }

    }

    override fun initListeners() {
        super.initListeners()
        binding.primary.setOnClickListener {
            //            val username: String = binding!!.username.text.toString().trim()
//            if (StringUtils.isBlank(username)) {
//                R.string.enter_phone_or_email.shortToast()
//                return@OnClickListener
//            }
            viewModel.login(
                binding.username.text.toString().trim(),
                binding.password.text.toString().trim(),
            )
        }

//        binding!!.register.setOnClickListener { v -> startActivity(RegisterActivity::class.java) }
    }
}