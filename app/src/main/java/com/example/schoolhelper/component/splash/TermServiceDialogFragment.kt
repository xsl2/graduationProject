package com.example.schoolhelper.component.splash

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.FragmentManager
import com.example.schoolhelper.R
import com.example.schoolhelper.fragment.BaseCommonFragment
import com.example.superui.process.SuperProcessUtil
import com.example.superui.util.ScreenUtil
import com.example.superui.util.SuperTextUtil

class TermServiceDialogFragment :BaseCommonFragment() {
    private lateinit var onAgreementClickListener: View.OnClickListener
    private lateinit var disagreeButton: Button
    private lateinit var primaryButton: Button
    private lateinit var contentView:TextView
    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_term_service,container,false)
    }

    override fun initViews() {
        super.initViews()
        //点击弹窗外面不可关闭
        isCancelable=false
        contentView = findViewById<TextView>(R.id.content)
        SuperTextUtil.setLinkColor(contentView,getColor(requireContext(),R.color.link))
        primaryButton = findViewById<Button>(R.id.primary)
        disagreeButton = findViewById<Button>(R.id.disagree)

    }

    override fun initDatum() {
        super.initDatum()
        val content=Html.fromHtml(getString(R.string.term_service_privacy_content))
        contentView.text=content
    }

    override fun initListeners() {
        super.initListeners()
        //同意按钮点击
        primaryButton.setOnClickListener {
            dismiss()
            onAgreementClickListener.onClick(it)
        }

        //不同意按钮点击
        disagreeButton.setOnClickListener {
            dismiss()
            SuperProcessUtil.killApp()
        }

    }

    override fun onResume() {
        super.onResume()
        val params:ViewGroup.LayoutParams=dialog!!.window!!.attributes
        params.width=((ScreenUtil.getScreenWith(requireContext())*0.9).toInt())
        params.height=ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes=params as WindowManager.LayoutParams
    }

    companion object {
        /**
         * 显示对话框
         */
        fun show(supportFragmentManager: FragmentManager, onAgreemnetClickListener: View.OnClickListener) {
            val dialogFragment= TermServiceDialogFragment()
            dialogFragment.onAgreementClickListener=onAgreemnetClickListener
            dialogFragment.show(supportFragmentManager,"TermServiceDialogFragment")
        }
    }

}