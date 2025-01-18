package com.example.schoolhelper.component.guide
import android.util.Log
import com.example.schoolhelper.R
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.databinding.ActivityGuideBinding
import com.example.schoolhelper.util.PreferenceUtil


class GuideActivity : BaseViewModelActivity<ActivityGuideBinding>() {
    private lateinit var adapter:GuideAdapter
    override fun initViews() {
        super.initViews()

    }

    override fun initDatum() {
        super.initDatum()
        adapter=GuideAdapter(this,supportFragmentManager)
        binding.list.adapter=adapter
        //让指示器根据列表控件配合工作
        binding.indicator.setViewPager(binding.list)

        //适配器注册数据源观察者
        adapter.registerDataSetObserver(binding.indicator.dataSetObserver)
        val datum:MutableList<Int> = ArrayList()
        datum.add(R.drawable.guide1)
        datum.add(R.drawable.guide2)
        datum.add(R.drawable.guide3)
        datum.add(R.drawable.guide4)
        datum.add(R.drawable.guide5)
        adapter.setDatum(datum)
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