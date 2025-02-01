package com.example.schoolhelper.component.splash

import android.Manifest
import android.os.Build
import android.util.Log
import com.example.schoolhelper.component.main.MainActivity
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.databinding.ActivitySplashBinding
import com.example.schoolhelper.component.guide.GuideActivity
import com.example.schoolhelper.util.DefaultPreferenceUtil
import com.example.schoolhelper.util.PreferenceUtil
import com.example.superui.date.SuperDateUtil
import com.example.superui.util.SuperDarkUtil
import com.permissionx.guolindev.PermissionX
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
/**
 * 启动界面
 *
 */
class SplashActivity : BaseViewModelActivity<ActivitySplashBinding>() {
//    private lateinit var tv_author:TextView

    override fun initViews() {
        super.initViews()
        //设置全屏，沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)
        if (SuperDarkUtil.isDark(this)) QMUIStatusBarHelper.setStatusBarDarkMode(this) else QMUIStatusBarHelper.setStatusBarLightMode(this)
//        tv_author=findViewById<TextView>(R.id.tv_author)

    }

    override fun initDatum() {
        super.initDatum()
        binding.tvAuthor.text = "designed by xsl ${SuperDateUtil.currentYear()}"
        if(DefaultPreferenceUtil.getInstance(this).isAcceptTermsServiceAgreement)
        {
            //已经同意了
            requestPermission()
        }
        else
        {
            showTermServiceAgreementDialog()
        }
    }
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionX.init(this).permissions(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_MEDIA_AUDIO,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
            )
        } else {
            PermissionX.init(this).permissions(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        }.request { allGranted, _, _ ->
            if (allGranted) {
                //暂缓一秒进入应用
                binding.root.postDelayed({
                    prepareNext()
                }, 1000)
            } else {
                //可以在这里弹出提示告诉用户为什么需要权限
                finish()
            }
        }
    }
    private fun prepareNext() {
        Log.d(TAG, "prepareNext: ")
        if(PreferenceUtil.isShowGuide())
        {
            startActivityAfterFinishThis(GuideActivity::class.java)
            return
        }
        startActivityAfterFinishThis(MainActivity::class.java)
    }



    private fun showTermServiceAgreementDialog()
    {
        TermServiceDialogDialogFragment.show(supportFragmentManager
        ) { Log.d(TAG, "onClick: primary")
            DefaultPreferenceUtil.getInstance(this).setAcceptTermsServiceAgreement()
        }
    }
    companion object{
        const val TAG="SplashActivity"
    }
}