package com.example.schoolhelper.component.guide
import android.util.Log
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.databinding.ActivityGuideBinding
import com.example.schoolhelper.util.PreferenceUtil

class GuideActivity : BaseViewModelActivity<ActivityGuideBinding>() {
    override fun initViews() {
        super.initViews()

    }

    override fun initListeners() {
        super.initListeners()
        binding.loginOrRegister.setOnClickListener {
            Log.d(TAG, "initListeners: loginOrRegister")
            setShowGuide()
        }
        binding.experienceNow.setOnClickListener{
            Log.d(TAG, "initListeners: experienceNow")
            setShowGuide()
        }
    }
    private fun setShowGuide()
    {
        PreferenceUtil.setShowGuide(false)
    }
    companion object{
        const val TAG="GuideActivity"
    }
}