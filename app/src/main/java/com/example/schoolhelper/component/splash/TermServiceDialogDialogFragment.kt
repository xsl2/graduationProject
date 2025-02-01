package com.example.schoolhelper.component.splash
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.FragmentManager
import com.example.schoolhelper.R
import com.example.schoolhelper.databinding.FragmentDialogTermServiceBinding
import com.example.schoolhelper.fragment.BaseViewModelDialogFragment
import com.example.superui.process.SuperProcessUtil
import com.example.superui.util.ScreenUtil
import com.example.superui.util.SuperTextUtil

class TermServiceDialogDialogFragment :BaseViewModelDialogFragment<FragmentDialogTermServiceBinding>() {
    private lateinit var onAgreementClickListener: View.OnClickListener
//    override fun getLayoutView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_dialog_term_service,container,false)
//    }

    override fun initViews() {
        super.initViews()
        //点击弹窗外面不可关闭
        isCancelable=false
        SuperTextUtil.setLinkColor(binding.content,getColor(requireContext(),R.color.link))

    }

    override fun initDatum() {
        super.initDatum()
        val content=Html.fromHtml(getString(R.string.term_service_privacy_content))
        binding.content.text=content
    }

    override fun initListeners() {
        super.initListeners()
        //同意按钮点击
        binding.primary.setOnClickListener {
            dismiss()
            onAgreementClickListener.onClick(it)
        }

        //不同意按钮点击
        binding.disagree.setOnClickListener {
            dismiss()
            SuperProcessUtil.killApp()
        }

    }

    override fun onResume() {
        super.onResume()
        val params:ViewGroup.LayoutParams=dialog!!.window!!.attributes
        params.width=((ScreenUtil.getScreenWidth(requireContext())*0.9).toInt())
        params.height=ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes=params as WindowManager.LayoutParams
    }

    companion object {
        /**
         * 显示对话框
         */
        fun show(supportFragmentManager: FragmentManager, onAgreemnetClickListener: View.OnClickListener) {
            val dialogFragment= TermServiceDialogDialogFragment()
            dialogFragment.onAgreementClickListener=onAgreemnetClickListener
            dialogFragment.show(supportFragmentManager,"TermServiceDialogFragment")
        }
    }

}