package com.example.schoolhelper.component.content

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolhelper.component.articledetail.ArticleDetailActivity
import com.example.schoolhelper.databinding.FragmentContentBinding
import com.example.schoolhelper.fragment.BaseViewModelFragment
import com.example.schoolhelper.util.Constant
import com.example.schoolhelper.util.ImageUtil
import com.google.common.collect.Lists
import com.wanglu.photoviewerlibrary.PhotoViewer
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils

class ContentFragment:BaseViewModelFragment<FragmentContentBinding>() {
    private lateinit var viewModel: ContentViewModel
    private lateinit var adapter: ContentAdapter
    private var isFirstShow: Boolean = true
    /**
     * 下面开始第一次使用MVVM架构，学习viewmodel
     * 也开始学习lifecycle协程。
     */
    override fun initDatum() {
        super.initDatum()
        //MVC架构就直接在这里面写，这里是C-controller层，进行MV层交互
//        lifecycleScope.launch {
//            DefaultNetWorkReposity.contents()
//        }

        val viewModelFactory = ContentViewModelFactory(requireArguments().getString(Constant.ID))
        viewModel=ViewModelProvider(this,viewModelFactory).get(ContentViewModel::class.java)

//        viewModel.tip.observe(this){
//            Toast.makeText(hostActivity,it,Toast.LENGTH_SHORT).show()
//            processRefreshAndLoadMoreStatus(false)
//        }
        initViewModel(viewModel)

        adapter=ContentAdapter(viewModel)
        binding.list.adapter=adapter
        lifecycleScope.launch {
            viewModel.data.collect{
                //数据处理
//                Log.d(TAG, "initDatum: ${it.data!![0].title}")
                if(StringUtils.isBlank(viewModel.lastId)){
                    adapter.submitList((it.data))
                }else{
                    it.data?.let {
                        adapter.addAll(it)
                    }
                }
//                adapter.submitList(it.data)
                processRefreshAndLoadMoreStatus(true, it.data?.isEmpty() ?: true)
            }
        }
//        viewModel.addMore()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 在组件处于 STARTED 状态时执行协程任务
                viewModel.toArticleDetail.collect { id ->
                    val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
                    intent.putExtra(Constant.ID, id)
                    startActivity(intent)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.previewMedia.collect {
                previewMedias(it)
            }
        }


    }
    private fun processRefreshAndLoadMoreStatus(success: Boolean, noMore: Boolean = false) {
        //传入false表示刷新失败
        binding.refresh.finishRefresh(500, success, false)

        //next=null，表示没有更多数据了
        binding.refresh.finishLoadMore(500, success, noMore)
    }

    override fun onError() {
        super.onError()
        processRefreshAndLoadMoreStatus(false)
    }


    private fun previewMedias(data: PreviewMediaPageData) {
        //将List转为ArrayList
        //因为图片框架需要的是ArrayList
        val medias = Lists.newArrayList<String>(data.medias)

        PhotoViewer.setData(medias)
            //设置当前位置
            .setCurrentPage(data.position)
            //他需要容器的目的是显示缩放动画
            .setImgContainer(data.view)
            //设置图片加载回调
            .setShowImageViewInterface(object : PhotoViewer.ShowImageViewInterface {
                override fun show(iv: ImageView, url: String) {
                    ImageUtil.show(
                        iv,
                        url
                    )
                }
            }) //启动界面
            .start(this)
    }

    override fun initViews() {
        super.initViews()
        // RecyclerView的设置，要在这里设置它是竖着滚动还是横着滚动
        binding.list.apply {
            layoutManager=LinearLayoutManager(hostActivity)//必须设置布局管理器是线性还是啥
            val decoration=DividerItemDecoration(requireContext(),RecyclerView.VERTICAL)//分割线实现，封装在主题中设定android:listDivider的样式
            addItemDecoration(decoration)
        }
    }

    override fun initListeners() {
        super.initListeners()
        //下拉刷新，用的第三方控件
        binding.refresh.setOnRefreshListener {//需要在gradle.properties中添加android.enableJetifier=true，该属性的意思是如果本项目中用到了安卓x或第三方库中有旧版依赖会有冲突，加上这个属性编译时就会把旧版依赖换成androidx中的依赖
            viewModel.addMore()
        }
        //上拉加载更多，用的第三方控件
        binding.refresh.setOnLoadMoreListener {
            viewModel.addMore(adapter.items.lastOrNull()?.id)
        }

    }

    override fun onResume() {
        super.onResume()
        if(isFirstShow)
        {
//            viewModel.addMore()
            binding.refresh.autoRefresh()
            isFirstShow=false
        }
    }


    companion object{
        const val TAG="ContentFragment"

        fun newInstance(categoryID:String?=null):ContentFragment{
            val args = Bundle()
            categoryID?.let {
                args.putString(Constant.ID,it)
            }
            val fragment = ContentFragment()
            fragment.arguments = args
            return fragment
        }
    }

}