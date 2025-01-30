package com.example.schoolhelper.component.guide
import android.content.Intent
import android.util.Log
import com.example.schoolhelper.component.main.MainActivity
import com.example.schoolhelper.R
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.config.Config
import com.example.schoolhelper.databinding.ActivityGuideBinding
import com.example.schoolhelper.util.Constant
import com.example.schoolhelper.util.PreferenceUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class GuideActivity : BaseViewModelActivity<ActivityGuideBinding>() {
    private lateinit var adapter:GuideAdapter


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
            //跳转到登录与注册页面
            setShowGuide()
            val intent= Intent(this, MainActivity::class.java)
            intent.action=Constant.ACTION_LOGIN
            startActivity(intent)
            finish()
        }
        binding.experienceNow.setOnClickListener{
            Log.d(TAG, "initListeners: experienceNow")
            //跳过注册直接进入主界面
            setShowGuide()
            startActivityAfterFinishThis(MainActivity::class.java)
//            testGet()
        }
    }

    private fun testGet() {
        val client = OkHttpClient()

        val url = Config.ENDPOINT + "v1/contents"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "onFailure: " + e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "onResponse: " + response.body!!.string())
            }

        })


    }

    private fun setShowGuide()
    {
        PreferenceUtil.setShowGuide(false)

    }
    companion object{
        const val TAG="GuideActivity"
    }
}